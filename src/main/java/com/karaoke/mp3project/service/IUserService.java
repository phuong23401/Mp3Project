package com.karaoke.mp3project.service;

import com.karaoke.mp3project.dto.request.SignupRequest;
import com.karaoke.mp3project.model.User;

import java.util.ArrayList;

public interface IUserService {
    ArrayList<User> findAll();

    void updateUser(User user);

    User findOne(Long id);

    User findByUsername(String name);

    boolean checkPassword(User user, String password);

    User findByVerificationCode(String code);

    Boolean verify(String code);

    Boolean check(User user);

    User createNewUser(SignupRequest signupRequest);

}
