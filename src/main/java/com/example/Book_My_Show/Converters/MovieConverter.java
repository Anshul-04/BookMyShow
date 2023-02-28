package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.EntryDTOs.MovieEntryDto;
import com.example.Book_My_Show.Models.Movie;

public class MovieConverter {

    public static Movie convertEntryDtoToEntity(MovieEntryDto movieEntryDto){

        //This is Converter
        Movie movieEntity = Movie.builder().movieName(movieEntryDto.getMovieName()).rating(movieEntryDto.getRating())
                .duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage())
                .genre(movieEntryDto.getGenre()).build();

        return movieEntity;
    }
}
