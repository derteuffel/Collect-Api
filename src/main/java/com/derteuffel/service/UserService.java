package com.derteuffel.service;

import com.derteuffel.data.User;
import com.derteuffel.repository.RoleRepository;
import com.derteuffel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by derteuffel on 10/10/2018.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
