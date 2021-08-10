package com.karaoke.mp3project.service;

import com.karaoke.mp3project.model.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IEmailSender {
    void send(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;
}

