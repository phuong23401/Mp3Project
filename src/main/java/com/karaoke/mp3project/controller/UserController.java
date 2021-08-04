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

    @PutMapping("/changeinfor")
    public ResponseEntity<MessageResponse> changeInforMations(@RequestBody UserCurrentUpdate userCurrentUpdate){
        User userCurrent = userDtService.getCurrentUser();
        System.out.println("user hien tai");
        System.out.println(userCurrent.toString());
        String message;
        if(userCurrent != null){
            userCurrent.setAvatarUrl(userCurrentUpdate.getAvatarUrl());
            userCurrent.setGender(userCurrentUpdate.getGender());
            userCurrent.setHobbies(userCurrentUpdate.getHobbies());
            userCurrent.setName(userCurrentUpdate.getName());
            userService.updateUser(userCurrent);
            message = "UPDATE PROFILE SUCCESSFULLY !";
            return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
        }else {
            message = "UPDATE PROFILE FAILED";
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(message));
        }
    }

    @PutMapping("/changepassword")
    public ResponseEntity<MessageResponse> changPassword(@RequestBody Password password){
        User userCurrent = userDtService.getCurrentUser();
        String message;
        if(userService.checkPassword(userCurrent, password.getPassword())){
            userCurrent.setPassword(passwordEncoder.encode(password.getNewPassword()));
            userService.updateUser(userCurrent);
            message = "CHANGE PASSWORD SUCCESSFULLY !";
            return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
        }else {
            message = "CHANGE PASSWORD FAILED";
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(message));
        }

    }

    @GetMapping("/getuser")
    public ResponseEntity<UserCurrentUpdate> getUserByToken() {
        User userCurrent = userDtService.getCurrentUser();
        UserCurrentUpdate userCurrentUpdate = new UserCurrentUpdate();

        userCurrentUpdate.setName(userCurrent.getName());
        userCurrentUpdate.setGender(userCurrent.getGender());
        userCurrentUpdate.setHobbies(userCurrent.getHobbies());
        userCurrentUpdate.setAvatarUrl(userCurrent.getAvatarUrl());

        System.out.println(userCurrent);
        return new ResponseEntity<>(userCurrentUpdate, HttpStatus.OK);
    }
}
