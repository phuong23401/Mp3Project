package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.CommentPlayList;
import com.karaoke.mp3project.model.CommentSong;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.ICommentSongService;
import com.karaoke.mp3project.service.impl.CommentPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import java.sql.Timestamp;
import java.util.TimeZone;

@Controller
@CrossOrigin("*")
@RequestMapping("/comment-song")
public class CommentSongController {
    @Autowired
    private ICommentSongService commentsongService;

    @Autowired
    private CommentPlayListService commentPlayListService;

    @Autowired
    private  UserDtService userDtService;

    @PostMapping
    private ResponseEntity<MessageResponse> createCommentSong(@RequestBody CommentSong commentSong){
        User user = userDtService.getCurrentUser();
        commentsongService.cmtSong(commentSong, user);
        String message = "Success";
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @PostMapping("/playlist")
    private ResponseEntity<MessageResponse> createCommentPlaylist(@RequestBody CommentPlayList commentPlayList){
        commentPlayListService.cmtPlaylist(commentPlayList);
        String message = "Success";
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
}
