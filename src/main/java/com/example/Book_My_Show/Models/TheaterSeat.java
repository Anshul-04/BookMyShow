package com.example.Book_My_Show.Models;

import com.example.Book_My_Show.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="theater_seats")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNumber; // like 1A ,2B, ..

    @Enumerated(value=EnumType.STRING)
    private SeatType seatType;  // like classic,Premium

    // UNI-DIRECTIONAL MAPPING
    //TheaterSeat is child wrt Theater
    @ManyToOne
    @JoinColumn
    private Theater theater;
}
