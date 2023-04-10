package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDTOs.UserEntryDto;
import com.example.Book_My_Show.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto){

        //Catiching the Exception at top most layer(Controller layer) instead of catching it in each Layer
        //for practising DRY and Clean code

        try {
            String response = userService.addUser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            String response = "User can not be added";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
