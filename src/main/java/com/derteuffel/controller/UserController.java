package com.derteuffel.controller;


import com.derteuffel.data.Role;
import com.derteuffel.data.User;
import com.derteuffel.repository.RoleRepository;
import com.derteuffel.repository.UserRepository;
import com.derteuffel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by derteuffel on 06/10/2018.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;





    @Autowired
    private UserService userService;

    @GetMapping("")
    public String allUsers(Model model){

        model.addAttribute("users",userRepository.findAll());
        List<User> users=userRepository.findAll();
        System.out.println(users);
        return "user/users";
    }


   /* @GetMapping("/{userId}")
    public String getUser(Model model, @PathVariable Long userId){
        User userOptional= userRepository.getOne(userId);
        model.addAttribute("user", userOptional);
        return "user/user";
    }*/

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
        return  "redirect:/user";
    }

    @GetMapping("/userForm")
    public String getform(Model model){
        model.addAttribute("user", new User());
        return "user/userForm";
    }

    @GetMapping("/update/{userId}")
    public String update(Model model, @PathVariable Long userId, HttpSession session){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userRepository.findByEmail(auth.getName());
        session.setAttribute("avatar", user.getImg());
        session.setAttribute("name", user.getName());
        model.addAttribute("user", userRepository.getOne(userId));
        return "user/user";
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createUser(@Valid User user, @RequestParam("file") MultipartFile file, BindingResult bindingResult, String role) {
        String fileName = fileUploadService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole(role);
        if (userRole== null){
            Role newRole= new Role("user");
            user.setRoles(new HashSet<Role>(Arrays.asList(newRole)));
            System.out.println(userRole);
        }else {
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        }


        FileUploadRespone fileUploadRespone = new FileUploadRespone(fileName, fileDownloadUri);
        user.setImg(fileUploadRespone.getFileDownloadUri());
        User user1=userRepository.findByEmail(user.getEmail());
        if (user1!=null){

            bindingResult.rejectValue("email", "user.error", "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()){
            return "user/userForm";
        }else {
          User savedUser=  userRepository.save(user);
            return "redirect:/these";
        }




    }

   /* @GetMapping("/updateForm/{userId}")
    public String updateForm(Model model, @PathVariable Long userId){
        model.addAttribute("user", userRepository.getOne(userId));
        return "inscription";
    }
    @PutMapping("/update")
    public String updateUser( User user, @RequestParam("file") MultipartFile file){

        String fileName= fileUploadService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        FileUploadRespone fileUploadRespone=new FileUploadRespone(fileName,fileDownloadUri);
        user.setImg(fileUploadRespone.getFileDownloadUri());



         User savedUser=userRepository.save(user);
        return "redirect:/user/"+ savedUser.getUserId();
    }*/
}
