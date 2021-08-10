package com.karaoke.mp3project.service.email;

import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.service.IEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService implements IEmailSender {
    @Autowired
    JavaMailSender mailSender;

    @Override
    public void send(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String verifyURL =siteURL + "/verify/" +user.getVerificationCode() ;
        System.out.println(verifyURL);
        String subject = "Please verify your registration";
        String senderName = "KaraOke";
        String mailContent = "<p>Dear " + user.getUsername()+",</p>";
        mailContent += "<p>Please click the link below to verify your registration:</p>";
        mailContent += "<h3><a href=\""+verifyURL+"\">VERIFY</a></h3>";
        mailContent += "<p>Thank you<br>The Kara Okee La</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("nguyenquangha2710@gmail.com",senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent,true);
        mailSender.send(message);
    }
}
