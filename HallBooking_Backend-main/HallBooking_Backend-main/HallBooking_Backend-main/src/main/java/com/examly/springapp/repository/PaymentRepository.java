package com.examly.springapp.repository;


import com.examly.springapp.entity.Payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

     @Query("SELECT b FROM Payment b")  
    List<Payment> findAll();

    @Query("SELECT b FROM Payment b WHERE b.id = :id")
    Payment findPaymentById(Long id);
}
