package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User,Long>{

     @Query("SELECT b FROM User b")  
    List<User> findAll();

    @Query("SELECT b FROM User b WHERE b.id = :id")
    User findUserById(Long id);
} 