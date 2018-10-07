package com.derteuffel.service;

import com.derteuffel.data.User;
import com.derteuffel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by derteuffel on 06/10/2018.
 */

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public ResponseEntity<Object> save(User user) {
         User savedUser=userRepository.save(user);

        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(savedUser.getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Object> update(User user, Long userId) {
        Optional<User> optional=userRepository.findById(userId);
        if (!optional.isPresent())
            return ResponseEntity.notFound().build();
        user.setUserId(userId);
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

    public User findById(Long userId) {

        Optional<User> optional= userRepository.findById(userId);
        return optional.get();
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
