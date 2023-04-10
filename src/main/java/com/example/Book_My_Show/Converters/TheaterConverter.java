package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.EntryDTOs.TheaterEntryDto;
import com.example.Book_My_Show.Models.Theater;

public class TheaterConverter {

    public static Theater convertEntryDtoToEntity(TheaterEntryDto theaterEntryDto){

        Theater theaterEntity = Theater.builder().name(theaterEntryDto.getName())
                                .location(theaterEntryDto.getLocation()).build();
        return  theaterEntity;
    }
}
