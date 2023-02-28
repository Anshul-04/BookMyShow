package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Models.Show;
import com.example.Book_My_Show.Models.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {
}
