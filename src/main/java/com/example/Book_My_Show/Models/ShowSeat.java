package com.example.Book_My_Show.Models;

import com.example.Book_My_Show.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="show_seats")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNumber;  // 1A,10B

    private boolean isBooked; 

    private int seatPrice;  //price for a particular seat

    private Date bookedAt;  //time of the seat booked(eg seat booked at "2023-04-30" Date)

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    //UNI-DIRECTIONAL MAPPING
    // ShowSeat is child wrt Show
    @ManyToOne
    @JoinColumn
    private Show show;

    // ShowSeat is child wrt Ticket
    @ManyToOne
    @JoinColumn
    private Ticket ticket;

}
