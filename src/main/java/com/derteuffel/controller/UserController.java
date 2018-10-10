package com.derteuffel.controller;


import com.derteuffel.data.User;
import com.derteuffel.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by derteuffel on 06/10/2018.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileUploadService fileUploadService;



    @GetMapping("/get")
    public List<User> allUsers(){
        return userRepository.findAll();
    }


    @GetMapping("{userId}")
    public User getUser(@PathVariable Long userId){
        Optional<User> userOptional= userRepository.findById(userId);
        return userOptional.get();
    }

    @GetMapping("/delete/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<Object> createUser( User user, @RequestParam("file") MultipartFile file){
        String fileName= fileUploadService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        FileUploadRespone fileUploadRespone=new FileUploadRespone(fileName,fileDownloadUri);
        user.setImg(fileUploadRespone.getFileDownloadUri());

        User savedUser= userRepository.save(user);
        URI location=  ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
                .buildAndExpand(savedUser.getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{userId}")
    @ResponseBody
    public ResponseEntity<Object> updateUser( User user, @PathVariable Long userId, @RequestParam("file") MultipartFile file){
        Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();

        user.setUserId(userId);
        String fileName= fileUploadService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        FileUploadRespone fileUploadRespone=new FileUploadRespone(fileName,fileDownloadUri);
        user.setImg(fileUploadRespone.getFileDownloadUri());

        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }
}
