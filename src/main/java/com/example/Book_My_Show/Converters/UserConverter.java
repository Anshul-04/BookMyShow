package com.example.Book_My_Show.Converters;

import com.example.Book_My_Show.EntryDTOs.UserEntryDto;
import com.example.Book_My_Show.Models.User;

public class UserConverter {


    //Static is kept to avoid calling it via objects/instances
    public static User convertEntryDtoToEntity(UserEntryDto userEntryDto){

        //Here we need to convert and save.
        /*{
        Old method : create an object and set attributes.
        New method : Now instead of setting each Attributes Separately ,with the help of @Builder
                     annotation which we wii define on that Entity of  which we have to make objects.
                     We can set the attributes in one line only.

        Eg :- User user = User.builder().age(userEntryDto.getAge()).build();
        --> This is a Converter,instead of use new keyword we are using this path
          We need convereter because we want our DTO to get converted into Entity as Repository only 
         deals with Entites.

        */

        User userEntity = User.builder().age(userEntryDto.getAge()).name(userEntryDto.getName())
                         .email(userEntryDto.getEmail()).mobileNumber(userEntryDto.getMobileNumber())
                         .address(userEntryDto.getAddress()).build();

             //the above line of code is CONVERETER(i.e Converting DTO to Entity)             

        return  userEntity;
    }
}
