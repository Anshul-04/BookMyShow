package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.EntryDTOs.ShowEntryDto;
import com.example.Book_My_Show.Models.Show;
import lombok.Data;

@Data
public class ShowConverter {

    public static Show convertEntryDtoToEntity(ShowEntryDto showEntryDto){

        Show showEntity =  Show.builder()
                          .showDate(showEntryDto.getShowDate())
                          .showTime(showEntryDto.getShowTime())
                          .showType(showEntryDto.getShowType())
                          .build();

        return showEntity;
    }
}
