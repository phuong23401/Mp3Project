package com.karaoke.mp3project.service.impl;


import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.repo.UserRepo;
import com.karaoke.mp3project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ArrayList<User> findAll() {
        return (ArrayList<User>) userRepo.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
    @Override
    public User findByVerificationCode(String code) {
        return userRepo.findByVerificationCode(code);
    }

    @Override
    public Boolean verify(String code) {
        User user = findByVerificationCode(code);
        if(user!=null){
            user.setVerifyEmail(true);
//            user.setAccountStatus(true);
            userRepo.save(user);
            return true;
        }else
            return false;
    }

    @Override
    public Boolean check(User user) {
        return user.getVerifyEmail();
    }
}