package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Converters.ShowConverter;
import com.example.Book_My_Show.EntryDTOs.ShowEntryDto;
import com.example.Book_My_Show.Enum.SeatType;
import com.example.Book_My_Show.Models.*;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String addShow(ShowEntryDto showEntryDto) throws Exception{

        // 1) Make SHow Entity and set all its attributes
        Show showEntity = ShowConverter.convertEntryDtoToEntity(showEntryDto);

        //We have to make Movie and Theater and set it.And to get it we can use movieId ,theaterId from DTO
        int movieId = showEntryDto.getMovieId();
        int theaterId = showEntryDto.getTheaterId();

        //Now we're creating Movie & Theater Entity

        Movie movieEntity = movieRepository.findById(movieId).get();
        Theater theaterEntity = theaterRepository.findById(theaterId).get();

        // Now we are setting the foreign key
        showEntity.setMovie(movieEntity);
        showEntity.setTheater(theaterEntity);

        //setting Bi-directional foreign key attribute the listOfShowSeatsEnity
        List<ShowSeat>  showSeatList = creatShowSeatList(showEntryDto,showEntity);
        showEntity.setListOfShowSeat(showSeatList);

        //Now we also need to update Parent Entites
        // As Movie & Theater also contain showList so we need to update it

        List<Show> showEntityList_1 = movieEntity.getShowList(); //getting showList from movie
        showEntityList_1.add(showEntity);         //adding showEntity to  showList
        movieEntity.setShowList(showEntityList_1);  // then setting Movie's showList

        movieRepository.save(movieEntity);

        //same thing for theater
        List<Show> showEntityList_2 = theaterEntity.getShowList();
        showEntityList_2.add(showEntity);
        theaterEntity.setShowList(showEntityList_2); //setting showList of Theater Entity

        theaterRepository.save(theaterEntity);

        return "The Show has been Added Successfully.";
    }

    private List<ShowSeat> creatShowSeatList(ShowEntryDto showEntryDto,Show showEntity) {

        /* We have passed Show in createShowSeatList method because we have to set all attribute of
         ShowSeat and show is a foreign key in it to set it also we have passed it. */

        /* Things we are going to do now

        1. We are creating TheaterSeatList and for each theaterSeat we are creating ShowSeat and setting its attributes
           like seatNo,seatType and seatPrice.
        2. TheaterSeat are actually physical Seat and ShowSeat are virtual seats for particular show.
           so we are trying to set physical seat value equals to virtual seat by setting all its required values,for each particular show
        3. As Theater already contain theaterSeatList so we can get it from there
        4.For  Theater entity  we can get it by showEntity

         */

        //getting theater from showEntity
        Theater theaterEntity = showEntity.getTheater();

        // we can get theaterSeatList from Theater as it's already have it.
        List<TheaterSeat> theaterSeatList = theaterEntity.getTheaterSeatList();

        List<ShowSeat> showSeatList = new ArrayList<>();

        //here we need theaterSeat because it already have info about seatNo,seatType
        for(TheaterSeat theaterSeat : theaterSeatList){
            //We need to create ShowSeat Entity ,we can use new keyword or @Builder method for it

            ShowSeat showSeat = new ShowSeat();

            //setting all its attributes
            showSeat.setSeatNumber(theaterSeat.getSeatNumber());
            showSeat.setSeatType(theaterSeat.getSeatType());

            //for setting seat price
            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setSeatPrice(showEntryDto.getClassicSeatPrice());
            }
            else{
                showSeat.setSeatPrice(showEntryDto.getPremiumSeatPrice());
            }

            showSeat.setBooked(false);
            //also set Show(parent) which is foreign key attribute
            showSeat.setShow(showEntity);

            //finally adding it to the list
            showSeatList.add(showSeat);
        }
        return showSeatList;
    }
}
