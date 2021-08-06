package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.CommentSong;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.ICommentSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("/comment-song")
public class CommentSongController {
    @Autowired
    private ICommentSongService commentsongService;

    @Autowired
    private  UserDtService userDtService;
    @PostMapping
    private ResponseEntity<MessageResponse> createCommentSong(@RequestBody CommentSong commentsong){
        User user = userDtService.getCurrentUser();
        commentsong.setUser(user);
        commentsongService.saveComment(commentsong);
        String message = "Success";
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
}
