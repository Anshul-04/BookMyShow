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

        Theater theater = TheaterConverter.convertEntryDtoToEntity(theaterEntryDto);

        // Setting all attributes of Theater before saving
        List<TheaterSeat> theaterSeatList = createTheaterSeatList(theaterEntryDto,theater);
        theater.setTheaterSeatList(theaterSeatList);

        theaterRepository.save(theater);
        return "Theater is Added successfully.";

    }

    private List<TheaterSeat> createTheaterSeatList (TheaterEntryDto theaterEntryDto,Theater theaterEntity){

        int numberOfClassicSeats = theaterEntryDto.getClassicSeatsCount();
        int numberOfPremiumSeats = theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        //Creating the Classic Seats
        for(int i=1;i<=numberOfClassicSeats;i++){

            // We need to make new TheaterSeat Entity and sets all its attributes
            TheaterSeat theaterSeat = TheaterSeat.builder().seatType(SeatType.CLASSIC)
                    .seatNumber(i+"C").theater(theaterEntity).build();  //Because we want our SeatNo to be like := 1C,2C,3C..

            theaterSeatList.add(theaterSeat);
        }

        //Creating the Premium Seats
        for(int i=0;i<=numberOfPremiumSeats;i++){

            // We need to make new TheaterSeat Entity and sets all its attributes
            TheaterSeat theaterSeat = TheaterSeat.builder().seatType(SeatType.PREMIUM)
                    .seatNumber(i+"P").theater(theaterEntity).build();  //Because we want our SeatNo to be like := 1P,2P,3P...

            theaterSeatList.add(theaterSeat);
        }
        //Not saving the child here
        return theaterSeatList;

    }
}
