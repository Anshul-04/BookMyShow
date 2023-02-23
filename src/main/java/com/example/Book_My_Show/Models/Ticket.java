package com.example.Book_My_Show.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private String price;   // the price at which  total no of seat is  booked
    private String movieName;
    private Date showDate;
    private LocalTime showTime;
    private String theater_Name;

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
