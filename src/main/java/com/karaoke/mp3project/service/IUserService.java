package com.karaoke.mp3project.service;

import com.karaoke.mp3project.model.User;

import java.util.ArrayList;

public interface IUserService {
    ArrayList<User> findAll();

    void updateUser(User user);

    User findOne(Long id);

    User findByUsername(String name);

    boolean checkPassword(User user, String password);
}
