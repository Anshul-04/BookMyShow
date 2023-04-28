package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.EntryDTOs.TicketEntryDto;
import com.example.Book_My_Show.Models.Ticket;

import lombok.Data;

@Data
public class TicketConverter {

    public static Ticket convertEntryDtoToEntity(TicketEntryDto ticketEntryDto){

        Ticket ticket = new Ticket();
        return ticket;

    }
    
}
