package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.model.LikeSong;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.impl.LikeSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class LikeController {
    @Autowired
    private UserDtService userDtService;
     @Autowired
    private LikeSongService likeSongService;

    @GetMapping("/like-song-by-user")
    public ResponseEntity<?> listLikeSongByUsername(){
        User user = userDtService.getCurrentUser();
        List<LikeSong> likeSongs = likeSongService.findByUserContaining(user.getUsername());
        if(likeSongs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(likeSongs,HttpStatus.OK);
    }
}
