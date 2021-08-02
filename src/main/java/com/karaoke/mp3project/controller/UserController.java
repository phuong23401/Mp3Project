package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.Password;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<MessageResponse> changeInforMations( @RequestBody User user){
//        User userOld =  userService.findOne(id);
//        User userCurrent =  userService.findByUsername(
//                SecurityContextHolder.getContext().getAuthentication().getName()
//        );
        User userCurrent = userDtService.getCurrentUser();
        System.out.println("user hien tai");
        System.out.println(userCurrent.toString());
        String message;
        if(userCurrent != null){
            userCurrent.setAvatarUrl(user.getAvatarUrl());
            userCurrent.setGender(user.getGender());
            userCurrent.setHobbies(user.getHobbies());
            userCurrent.setName(user.getName());
            userService.updateUser(userCurrent);
            message = "Cập nhât thông tin thành công!";
            return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
        }else {
             message = "Cập nhật thất bại, kiểm tra lại!";
            return new ResponseEntity<>(new MessageResponse(message), HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @PutMapping("/changepassword")
    public ResponseEntity<MessageResponse> changPassword(@RequestBody Password password){
        User userCurrent = userDtService.getCurrentUser();
        String message;
        if(userService.checkPassword(userCurrent, password.getPassword())){
            userCurrent.setPassword(passwordEncoder.encode(password.getNewPassword()));
            userService.updateUser(userCurrent);
            message = "Đã đổi mật khẩu thành công!";
        }else {
            message = "Đổi mật khẩu thất bại!";
        }
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @PostMapping("/getuser")
    public ResponseEntity<User> getUserByToken() {
//        User userCurrent = userDtService.getCurrentUser();
        User userCurrent =  userService.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        System.out.println(userCurrent);
        return new ResponseEntity<>(userCurrent, HttpStatus.OK);
    }
}
