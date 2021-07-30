package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("song")
@RestController
public class SongController {
    @Autowired
    private SongService songService;































    @RequestMapping(value = "/songs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> getAllSong() {
        List<Song> songList = songService.findAll();
        if (songList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }
}
