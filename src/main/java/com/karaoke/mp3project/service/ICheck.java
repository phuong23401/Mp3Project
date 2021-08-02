package com.karaoke.mp3project.service;

import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.model.Check;

import java.util.List;

public interface ICheck {
    List<Check> findAll();

    void updateCheck(User user);

    Check save(Check checker);
}
