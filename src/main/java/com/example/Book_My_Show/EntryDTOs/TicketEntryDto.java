package com.example.Book_My_Show.EntryDTOs;

import java.util.*;

import lombok.Data;

@Data
public class TicketEntryDto {

    private int showId;

    private int userId;

    private List<String> requestedSeats = new ArrayList<>();
    
}
