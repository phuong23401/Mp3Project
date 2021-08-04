package com.karaoke.mp3project.service.impl;

import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.model.Check;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.ICheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CheckService implements ICheck {
    @Autowired
    ICheck iCheck;

    @Autowired
    UserDtService userDtService;


    @Override
    public List<Check> findAll() {
        return iCheck.findAll();
    }

    @Override
    public void updateCheck(User user) {

    }

    @Override
    public Check save(Check checker) {
        User user = userDtService.getCurrentUser();
        checker.setUser(user);
        return iCheck.save(checker);
    }
}
