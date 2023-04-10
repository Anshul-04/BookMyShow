package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDTOs.MovieEntryDto;
import com.example.Book_My_Show.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;
    
    @PostMapping("/add")
    public ResponseEntity<String > addMovie (@RequestBody()MovieEntryDto movieEntryDto){

        try {
            String response = movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            String response = "Movie can not br added";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
