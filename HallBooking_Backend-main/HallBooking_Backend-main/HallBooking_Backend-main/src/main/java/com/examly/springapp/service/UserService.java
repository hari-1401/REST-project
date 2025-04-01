package com.examly.springapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.examly.springapp.entity.User;
import com.examly.springapp.repository.UserRepository;

@Service
public class UserService {
    
    final public UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }

    public User getUser(Long id)
    {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"+id));
    }

    public User updateUser(Long id,String name,String email,String Phone)
    {
        User user=userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"+id));
        if(name!=null)
        {
            user.setName(name);
        }
        if(email!=null)
        {
            user.setEmail(email);
        }
        if(Phone!=null)
        {
            user.setPhone(Phone);
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id)
    {
        if(!userRepository.existsById(id))
        {
            throw new RuntimeException("user not found"+id);
        }
        userRepository.deleteById(id);
    }
      public Page<User> getAllPage(Pageable pg)
     {
        return userRepository.findAll(pg);
     }
     public User getUser(long id) {
    return userRepository.findById(id).orElse(null);  // Return Hall or null if not found
}

}
