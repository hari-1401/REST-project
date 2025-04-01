package com.examly.springapp.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.Booking;


@Repository
public interface BookRepository extends JpaRepository <Booking,Long>{

    
    @Query("SELECT b FROM Booking b")  
    List<Booking> findAll();

    @Query("SELECT b FROM Booking b WHERE b.id = :id")
    Booking findBookById(Long id);
}