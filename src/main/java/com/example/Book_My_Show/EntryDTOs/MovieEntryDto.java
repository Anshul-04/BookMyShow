package com.example.Book_My_Show.EntryDTOs;

import com.example.Book_My_Show.Enum.Genre;
import com.example.Book_My_Show.Enum.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MovieEntryDto {

    private String movieName;

    private double rating;

    private int duration;

    private Language language;

    private Genre genre;
}
