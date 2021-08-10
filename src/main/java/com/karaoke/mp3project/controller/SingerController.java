package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.Singer;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.service.impl.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("singers")
@RestController
public class SingerController {
    @Autowired
    private SingerService singerService;

    @PostMapping("/create")
    public ResponseEntity<?> createSinger(@Valid @RequestBody Singer singer) {
        singerService.saveSinger(singer);
        return new ResponseEntity<>(singer, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateSong(@PathVariable Long id,
                                        @Valid @RequestBody Singer newSinger){
        Optional<Singer> singer = singerService.findOne(id);
        if (!singer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            if (newSinger.getAvatarUrl() == null || newSinger.getAvatarUrl().trim().isEmpty()) {
                return new ResponseEntity<>(new MessageResponse("noavatar"), HttpStatus.OK);
            }
            newSinger.setId(id);
            singerService.saveSinger(newSinger);
            return new ResponseEntity<>(new MessageResponse("Done"), HttpStatus.OK);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSinger(@PathVariable Long id) {
        Optional<Singer> singer = singerService.findOne(id);
        if (!singer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        singerService.deleteSinger(singer.get().getId());
        return new ResponseEntity<>(new MessageResponse("Done"), HttpStatus.OK);
    }



    @PostMapping("/test")
    public ResponseEntity<?> findSingerByName(@Valid @RequestBody String name) {
        Singer singer =  singerService.findByName(name);
        if (singer==null) {
            return new ResponseEntity<>(new MessageResponse("noObj"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(singer, HttpStatus.OK);
    }

    @GetMapping("/getSingerBySongId/{id}")
    public ResponseEntity<List<Singer>> getSingerBySongId(@PathVariable Long id) {
        List<Singer> singerList = singerService.getSingerBySongId(id);
        if (singerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(singerList, HttpStatus.OK);
    }
}
