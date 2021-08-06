package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("home")
@RestController
public class HomeController {
    @Autowired
    private SongService songService;

    @GetMapping("/new")
    public ResponseEntity<Iterable<Song>> getAllSongNew() {
        List<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
