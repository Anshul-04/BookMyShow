package com.example.Book_My_Show.Models;

import com.example.Book_My_Show.Enum.ShowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="shows")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /* We are Using LocalDate and LocalTime so we can customized the DATE TIME formate */

    // LocalDate represents a date without a time zone or time of day, such as "2023-04-01"
    private LocalDate showDate;

    // LocalTime represents a time of day without a date or time zone, such as "15:30:00".
    private LocalTime showTime;

    @Enumerated(value=EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    // UNI-DIRECTIONAL MAPPING
    // Show is child wrt Theater
    @ManyToOne
    @JoinColumn
    private Theater theater;

    // show is also child wrt Movie
    @ManyToOne
    @JoinColumn
    private Movie movie;

    // BI-DIRECTIONAL MAPPING
    // Show is parent wrt Ticket
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<Ticket> ticketList = new ArrayList<>();

    // Show is parent wrt ShowSeat
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<ShowSeat> listOfShowSeat = new ArrayList<>();

}
