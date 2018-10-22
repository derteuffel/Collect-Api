package com.derteuffel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by derteuffel on 20/10/2018.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String login(){
        return "index";
    }
}
