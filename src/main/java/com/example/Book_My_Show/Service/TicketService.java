package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Converters.TicketConverter;
import com.example.Book_My_Show.Models.Show;
import com.example.Book_My_Show.Models.ShowSeat;
import com.example.Book_My_Show.Models.Ticket;
import com.example.Book_My_Show.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Book_My_Show.EntryDTOs.TicketEntryDto;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;
 
 
    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception{

        // 1. Create Ticket Entity from EntryDto : Convert Dto to Entity
        Ticket ticketEntity = TicketConverter.convertEntryDtoToEntity(ticketEntryDto);

        //Do some validation to check if Requested Seats are available or not
        boolean isValidRequest = checkValididtyOfRequestedSeats(ticketEntryDto);

        if(isValidRequest==false){
            throw new Exception("Requested Seats are not Available");
        }

        //We calculate the total amount of booked Seats

        int showId = ticketEntryDto.getShowId();
        Show showEntity = showRepository.findById(showId).get();

        List<ShowSeat> showSeatList = showEntity.getListOfShowSeat();

        List<String> requestedSeatsList  = ticketEntryDto.getRequestedSeats();

        int totalAmount =0;
        for(ShowSeat showSeatEntity : showSeatList){

            //getting the seatNo of each showSeatEntity
            String seatNo = showSeatEntity.getSeatNumber();

            //if requestedSeats is available then add its price to total amount
            if(requestedSeatsList.contains(seatNo)){
                totalAmount += showSeatEntity.getSeatPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }


        //setting total Amount of ticket
        ticketEntity.setTotalAmount(totalAmount);

        //setting the other attributes for ticketEntity
        ticketEntity.setMovieName(showEntity.getMovie().getMovieName());
        ticketEntity.setTheaterName(showEntity.getTheater().getName());
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());


        //We need to set that string that talked about requested Seats
        String allotedSeats = getAllotedSeatsfromShowSeats(requestedSeatsList);
        ticketEntity.setBookedSeats(allotedSeats);


        //setting foreign key attributes
        int userId = ticketEntryDto.getUserId();
        User userEntity = userRepository.findById(userId).get();

        ticketEntity.setUser(userEntity);
        ticketEntity.setShow(showEntity);

        //before saving the parent also save the ticket(child)
        ticketEntity = ticketRepository.save(ticketEntity);

        //Save the parent
        List<Ticket> ticketList = showEntity.getBookedTicketList();
        ticketList.add(ticketEntity);
        showEntity.setBookedTicketList(ticketList);

        showRepository.save(showEntity);

        List<Ticket> ticketList1 = userEntity.getBookedTicketsList();
        ticketList1.add(ticketEntity);
        userEntity.setBookedTicketsList(ticketList1);

        userRepository.save(userEntity);

        return "Ticket has been successfully added";


    }

    private  String getAllotedSeatsfromShowSeats(List<String> requestedSeatsList){

        String result ="";
        for(String seat : requestedSeatsList){
            result = result +seat +", ";
        }
        return result;
    }

    private boolean checkValididtyOfRequestedSeats(TicketEntryDto ticketEntryDto){

        int showId = ticketEntryDto.getShowId();

        List<String> requestedSeatsList = ticketEntryDto.getRequestedSeats();

        Show showEntity = showRepository.findById(showId).get();

        List<ShowSeat> showSeatList = showEntity.getListOfShowSeat();

        //Iterating over the list Of Seats for that particular show
        for(ShowSeat showSeatEntity : showSeatList){

            String seatNo = showSeatEntity.getSeatNumber();

            if(requestedSeatsList.contains(seatNo)){
                if(showSeatEntity.isBooked()==true){
                    return false;  //Since this seat cant be occupied : returning false
                }
            }
        }
        //All the seats requested are available
        return true;

    }
}
