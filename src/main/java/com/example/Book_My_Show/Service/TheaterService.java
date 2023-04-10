package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Converters.TheaterConverter;
import com.example.Book_My_Show.EntryDTOs.TheaterEntryDto;
import com.example.Book_My_Show.Enum.SeatType;
import com.example.Book_My_Show.Models.Theater;
import com.example.Book_My_Show.Models.TheaterSeat;
import com.example.Book_My_Show.Repository.TheaterRepository;
import com.example.Book_My_Show.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto) throws  Exception{
        
        
        /*
         1.create theater seats
         2 .i need to save theater: i need Theater Entity
         3.always sets the attributes before saving
         
         */

        // Do some validations
        if(theaterEntryDto.getName()==null || theaterEntryDto.getLocation()==null){
            throw new Exception("Name and Location is not Valid.");
        }

        Theater theater = TheaterConverter.convertEntryDtoToEntity(theaterEntryDto);

        // Setting all attributes of Theater before saving
        /*
          In theater name,location attributes are sent from postman so, we don't need to set them.
          But we have foreign key also we need to set them .Here we're only setting TheaterSeatList for now.

          In below list createTheaterSeatList(theaterEntryDto,theater) method we are also passing theater as parameter
          because in TheaterSeat entity we have to set all its attribute like seatNumber,seatType and theater(which is foreign key)
          that's why we are passing theater too.
         */
        List<TheaterSeat> theaterSeatList = createTheaterSeatList(theaterEntryDto,theater); //this method is below 
        theater.setTheaterSeatList(theaterSeatList);

        theaterRepository.save(theater);
        return "Theater is Added successfully.";

    }

    private List<TheaterSeat> createTheaterSeatList (TheaterEntryDto theaterEntryDto,Theater theaterEntity){

        /* We have passed Theater in createTheaterSeatList method because we have to set all attribute of
         TheaterSeat and theater is a foreign key in it to set it also we have passed it. */

        int numberOfClassicSeats = theaterEntryDto.getClassicSeatsCount();
        int numberOfPremiumSeats = theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        //Creating the Classic Seats
        for(int i=1;i<=numberOfClassicSeats;i++){

            // We need to make new TheaterSeat Entity and sets all its attributes
            TheaterSeat theaterSeat = TheaterSeat.builder().seatType(SeatType.CLASSIC)
                    .seatNumber(i+" C").theater(theaterEntity).build();
            //Because we want our SeatNo to be like := 1C,2C,3C..

            theaterSeatList.add(theaterSeat);
        }

        //Creating the Premium Seats
        for(int i=1;i<=numberOfPremiumSeats;i++){

            // We need to make new TheaterSeat Entity and sets all its attributes
            TheaterSeat theaterSeat = TheaterSeat.builder().seatType(SeatType.PREMIUM)
                    .seatNumber(i+" P").theater(theaterEntity).build();
            //Because we want our SeatNo to be like := 1P,2P,3P...

            theaterSeatList.add(theaterSeat);
        }
        //Not saving the child here
        return theaterSeatList;

    }
}
