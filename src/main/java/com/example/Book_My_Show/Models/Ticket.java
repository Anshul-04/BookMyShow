package com.example.Book_My_Show.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="tickets")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
  
    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private String theaterName;

    private int totalAmount;   // the price of all booked seats

    private String ticketID = UUID.randomUUID().toString(); //generate ticketId randomly

    private String bookedSeats; //this is for the requested seat while booking the seat

    // UNIDIRECTIONAL MAPPING
    // Ticket is child wrt User
    @ManyToOne
    @JoinColumn
    private User user;

    // Ticket is child wrt Show
    @ManyToOne
    @JoinColumn
    private Show show;

    //BI-DIRECTIONAL MAPPING
     // Ticket is parent wrt ShowSeat
    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
    private List<ShowSeat> showSeatList = new ArrayList<>();

}
