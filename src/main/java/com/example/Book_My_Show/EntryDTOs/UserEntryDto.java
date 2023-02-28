package com.example.Book_My_Show.EntryDTOs;

import lombok.Data;

@Data
public class UserEntryDto {

    private String name;
    private String email;
    private int age;
    private  String mobileNumber;
    private  String address;

    public UserEntryDto() {
    }
}
