package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.LikeSong;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.impl.LikeSongService;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.impl.LikeSongService;
import com.karaoke.mp3project.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("home")
@RestController
public class HomeController {
    @Autowired
    private SongService songService;
    @Autowired
    private UserDtService userDtService;
    @Autowired
    private LikeSongService likeSongService;

    @GetMapping("/new")
    public ResponseEntity<Iterable<Song>> getAllSongNew() {
        List<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    @GetMapping(value = "/top2mostlistened", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> top10SongsNew() {
        List<Song> songList = songService.findAllByCreationTimeOrderByCreationTime();
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

    //    @GetMapping("/toplisten")
//    public ResponseEntity<?> topListen() {
//        List<Song> songs = songService.findAllByOrderByListenSong();
//        return new ResponseEntity<>(songs,HttpStatus.OK);
//    }
    @GetMapping("/count-listen-song/{id}")
    public ResponseEntity<?> getSongListenById(@PathVariable("id") Long id) {
        try {
            Song song = songService.findOne(id).orElseThrow(EntityNotFoundException::new);
            song.setNumberOfView(song.getNumberOfView() + 1);
            songService.saveSong(song);
            return new ResponseEntity<>(song, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/song-like-up/{id}")
    public ResponseEntity<?> getSongLikedById(@PathVariable Long id) {
        try {
            Song song = songService.findOne(id).orElseThrow(EntityNotFoundException::new);
            User user =  userDtService.getCurrentUser();
            List<LikeSong> likeSongs = likeSongService.findByUserContaining(user.getUsername());
            if (likeSongs.size() != 0){
                for (int i = 0; i < likeSongs.size(); i++) {
                    if (likeSongs.get(i).getSong().equals(song.getName())){
                        likeSongService.deleteLikesong(likeSongs.get(i).getId());
                        song.setCountLike(song.getCountLike() -1);
                        songService.saveSong(song);
                        return  new ResponseEntity<>(song,HttpStatus.OK);
                    }

                }
            }
            LikeSong likeSong = new LikeSong();
            likeSong.setSong(song.getName());
            likeSong.setUser(user.getUsername());
            likeSongService.save(likeSong);
            song.setCountLike(song.getCountLike() +1);
            songService.saveSong(song);
            return new ResponseEntity<>(song,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(new MessageResponse(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }


}
