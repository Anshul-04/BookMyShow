package com.example.Book_My_Show.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column( unique = true ,nullable = false)
    private String email;

    private int age;

    @NonNull
    @Column(unique = true)
    private  String mobileNumber;

    private  String address;

    // BI_DIRECTIONAL MAPPING
    // User is parent wrt Ticket
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Ticket> bookedTicketsList = new ArrayList<>();


}
