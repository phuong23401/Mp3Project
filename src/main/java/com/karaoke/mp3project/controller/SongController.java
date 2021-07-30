package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;

@CrossOrigin(origins ="*")
@RequestMapping("song")
@RestController
public class SongController {
    @Autowired
    private SongService songService;

    @PostMapping("/create")
    public ResponseEntity<?> createSong(@Valid @RequestBody Song song){
        if(song.getAvatarUrl()==null||song.getAvatarUrl().trim().isEmpty()){
            return new ResponseEntity<>(new MessageResponse("noavatar"), HttpStatus.OK);
        }
        if(song.getFileUrl()==null||song.getFileUrl().trim().isEmpty()){
            return new ResponseEntity<>(new MessageResponse("nomp3url"), HttpStatus.OK);
        }
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        Timestamp upDateTime = new Timestamp(System.currentTimeMillis());
        song.setCreatedTime(createdTime);
        song.setUpdatedTime(upDateTime);
        songService.saveSong(song);
        return new ResponseEntity<>(new MessageResponse("Done"), HttpStatus.OK);
    }

}
