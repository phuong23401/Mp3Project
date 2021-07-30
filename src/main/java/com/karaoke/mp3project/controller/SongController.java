package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequestMapping("song")
@RestController
public class SongController {
    @Autowired
    private SongService songService;

}
