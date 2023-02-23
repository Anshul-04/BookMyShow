package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.DTOs.UserEntryrDto;
import com.example.Book_My_Show.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add_user")
    public String addUser(@RequestBody UserEntryrDto userEntryrDto){
        return userService.addUser(userEntryrDto);
    }
}
