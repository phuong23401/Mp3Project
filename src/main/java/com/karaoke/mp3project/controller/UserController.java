package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.Password;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.model.UserCurrentUpdate;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserDtService userDtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Long id){
        return new ResponseEntity<>(userService.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserID(@PathVariable("id") Long id){
        User user = userService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/changeinfor")
    public ResponseEntity<MessageResponse> changeInforMations(@RequestBody UserCurrentUpdate userCurrentUpdate){
        User userCurrent = userDtService.getCurrentUser();
        String message;
        if(userCurrent != null){
            userCurrent.setAvatarUrl(userCurrentUpdate.getAvatarUrl());
            userCurrent.setGender(userCurrentUpdate.getGender());
            userCurrent.setHobbies(userCurrentUpdate.getHobbies());
            userCurrent.setName(userCurrentUpdate.getName());
            userService.updateUser(userCurrent);
            message = "Update profile success fully !";
            return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
        }else {
            message = "Update profile failed";
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse(message));
    }

    @PutMapping("/changepassword")
    public ResponseEntity<MessageResponse> changPassword(@RequestBody Password password){
        User userCurrent = userDtService.getCurrentUser();
        String message;
        if(userService.checkPassword(userCurrent, password.getPassword())){
            userCurrent.setPassword(passwordEncoder.encode(password.getNewPassword()));
            userService.updateUser(userCurrent);
            message = "Change password success fully !";
            return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
        }else {
            message = "Change password failed";
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse(message));
    }

    @GetMapping("/getuser")
    public ResponseEntity<UserCurrentUpdate> getUserByToken() {
        User userCurrent = userDtService.getCurrentUser();
        UserCurrentUpdate userCurrentUpdate = new UserCurrentUpdate();
        userCurrentUpdate.setName(userCurrent.getName());
        userCurrentUpdate.setGender(userCurrent.getGender());
        userCurrentUpdate.setHobbies(userCurrent.getHobbies());
        userCurrentUpdate.setAvatarUrl(userCurrent.getAvatarUrl());
        return new ResponseEntity<>(userCurrentUpdate, HttpStatus.OK);
    }
}
