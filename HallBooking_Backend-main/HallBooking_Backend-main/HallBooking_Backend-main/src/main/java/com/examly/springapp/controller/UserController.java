package com.examly.springapp.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.examly.springapp.entity.User;
import com.examly.springapp.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/user")
public class UserController {
   public UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping

    public ResponseEntity<Object> createUser(@RequestBody User user)
    {
        User User2 =userService.createUser(user);
        return ResponseEntity.status(200).body(User2);
    }

    @GetMapping

    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> users =userService.getAllUser();
        return ResponseEntity.status(200).body(users);
    }

    // @GetMapping("{id}")

    // public ResponseEntity<Object> getUser(@PathVariable("id") Long id)
    // {
    //     User user =userService.getUser(id);
    //     return ResponseEntity.status(200).body(user);
    // }

    @PutMapping("{id}")

    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id,@RequestBody User user)
    {
        userService.updateUser(id,user.getName(),user.getEmail(),user.getPhone());
        return ResponseEntity.ok("Updated Success");
    }

    @DeleteMapping("{id}")

    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted SuccessFully");
    }
    
    @GetMapping("paginate")
     public Page<User>getAllPage(Pageable pg)
     {
        return userService.getAllPage(pg);
     }

     @GetMapping("{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") long id) {
    User user= userService.getUser(id);
    if (user == null)
    {
        return ResponseEntity.status(200).body("Book not found with ID: " + id);
    }
    return ResponseEntity.ok(user);
    }
    
    
}
