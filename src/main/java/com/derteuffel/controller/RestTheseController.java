package com.derteuffel.controller;

import com.derteuffel.data.These;
import com.derteuffel.data.User;
import com.derteuffel.repository.TheseRepository;
import com.derteuffel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RestTheseController{
    @Autowired
    private TheseRepository theseRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/these/getOne/{theseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public These findById(@PathVariable Long theseId) {

        Optional<These> theseOptional=theseRepository.findById(theseId);
        System.out.println(theseOptional.get().getLevel());
        return theseOptional.get();


    }

    @GetMapping("/user/get/{userId}")
    public User findOne(@PathVariable Long userId){
        Optional<User> userOptional= userRepository.findById(userId);
        return userOptional.get();
    }


}
