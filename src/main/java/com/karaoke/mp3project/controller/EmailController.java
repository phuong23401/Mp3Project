package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private UserService userService;

    @GetMapping("/verify/{code}")
    public String verify(@PathVariable String code){
        boolean verifed = userService.verify(code);
        if(verifed){
            return "Verify Success!";
        }
        return "Can Not Verify !!!";
    }
}
