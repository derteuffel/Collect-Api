package com.derteuffel.controller;


import com.derteuffel.data.User;
import com.derteuffel.repository.UserRepository;
import com.derteuffel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by derteuffel on 06/10/2018.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger LOG= LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteById(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Object> update(@RequestBody User user, @PathVariable Long userId) {
        return userService.update(user, userId);
    }
}
