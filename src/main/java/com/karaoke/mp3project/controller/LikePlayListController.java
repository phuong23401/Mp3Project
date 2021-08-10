package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.LikePlayList;
import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.impl.LikePlayListService;
import com.karaoke.mp3project.service.impl.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/like-play-list")
@CrossOrigin("*")
public class LikePlayListController {
    @Autowired
    private LikePlayListService likePlayListService;
    @Autowired
    private PlayListService playlistService;
    @Autowired
    private UserDtService userDtService;

    @PostMapping
    public ResponseEntity<MessageResponse> likePlayList(@RequestBody LikePlayList likePlayList){
        likePlayListService.saveLikeplaylist(likePlayList);
        String message = "Success";
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Iterable<LikePlayList>> getAllLikeplaylist(){
        Iterable<LikePlayList> likeplaylists = likePlayListService.findAll();
        return new ResponseEntity<>(likeplaylists, HttpStatus.OK);
    }

}
