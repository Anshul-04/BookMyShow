package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Converters.MovieConverter;
import com.example.Book_My_Show.EntryDTOs.MovieEntryDto;
import com.example.Book_My_Show.Models.Movie;
import com.example.Book_My_Show.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

   public String addMovie(MovieEntryDto movieEntryDto) throws Exception{

       // calling the MovieConverter from our Convereter package

       Movie movie = MovieConverter.convertEntryDtoToEntity(movieEntryDto);
       movieRepository.save(movie);
       return "Movie added Successfully";

   }

}
