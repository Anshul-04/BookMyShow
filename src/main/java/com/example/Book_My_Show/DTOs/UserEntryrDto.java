package com.example.Book_My_Show.DTOs;

import lombok.Data;

@Data
public class UserEntryrDto {

    private String name;
    private String email;
    private int age;
    private  String mobileNumber;
    private  String address;

    public UserEntryrDto() {
    }
}
