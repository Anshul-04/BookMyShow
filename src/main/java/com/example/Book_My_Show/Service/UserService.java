package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.DTOs.UserEntryrDto;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryrDto userEntryrDto){

        //Here we need to convert and save.
        /*
            Old method : create an object and set attributes.
            New method : Now instead of setting each Attributes Separately ,with the help of @Build
                         annotation which we wii define on that Entity of  which we have to make objects.
                         We can set the attributes in one line only.

            Eg :- User user = User.builder().age(userEntryDto.getAge()).build();
         */

        User userEntity = User.builder().age(userEntryrDto.getAge()).name(userEntryrDto.getName()).email(userEntryrDto.getEmail()).mobileNumber(userEntryrDto.getMobileNumber()).address(userEntryrDto.getAddress()).build();

        try{
            userRepository.save(userEntity);
        }
        catch (Exception e){
            return "User could not added.";
        }
        return "User Added Successfully";
    }
}
