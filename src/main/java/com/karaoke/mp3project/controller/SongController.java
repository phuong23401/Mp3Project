package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.LikeSong;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.impl.LikeSongService;
import com.karaoke.mp3project.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("song")
@RestController
public class SongController {
    @Autowired
    private UserDtService userDtService;
    @Autowired
    private SongService songService;
    @Autowired
    private LikeSongService likeSongService;


    @PostMapping("/create")
    public ResponseEntity<?> createSong(@Valid @RequestBody Song song) {
        if (song.getAvatarUrl() == null || song.getAvatarUrl().trim().isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("noavatar"), HttpStatus.OK);
        }
        if (song.getFileUrl() == null || song.getFileUrl().trim().isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("nomp3url"), HttpStatus.OK);
        }
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        Timestamp upDateTime = new Timestamp(System.currentTimeMillis());
        Long viewnumber = Long.valueOf(0);
        song.setCreatedTime(createdTime);
        song.setUpdatedTime(upDateTime);
        song.setNumberOfView(viewnumber);
        songService.saveSong(song);
        return new ResponseEntity<>(new MessageResponse("Done"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        Optional<Song> song = songService.findOne(id);
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        songService.deleteSong(song.get().getId());
        return new ResponseEntity<>(new MessageResponse("Done"), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateSong(@PathVariable Long id,
                                        @Valid @RequestBody Song newSong){
        Optional<Song> song = songService.findOne(id);
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
//            if (newSong.getAvatarUrl() == null || newSong.getAvatarUrl().trim().isEmpty()) {
//                return new ResponseEntity<>(new MessageResponse("noavatar"), HttpStatus.OK);
//            }
//            if (newSong.getFileUrl() == null || newSong.getFileUrl().trim().isEmpty()) {
//                return new ResponseEntity<>(new MessageResponse("nomp3url"), HttpStatus.OK);
//            }
            Timestamp createdTime = new Timestamp(System.currentTimeMillis());
            Timestamp upDateTime = new Timestamp(System.currentTimeMillis());
            newSong.setCreatedTime(createdTime);
            newSong.setUpdatedTime(upDateTime);
            newSong.setId(id);
            songService.saveSong(newSong);
            return new ResponseEntity<>(new MessageResponse("Done"), HttpStatus.OK);
        }
    }
    @GetMapping("/page/song")
    public ResponseEntity<?> pageSong(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Song> songPage = songService.findAllSong(pageable);
        if(songPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songPage, HttpStatus.OK);
    }



    @RequestMapping(value = "/songs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> getAllSong() {
        List<Song> songList = songService.findAll();
        if (songList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

    @GetMapping(value = "/search-song", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Song>> searchNameSong(@RequestParam String name) {
        String nameSong = "%" + name + "%";
        List<Song> songList = songService.findAllByNameSong(nameSong);
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<Iterable<Song>> getAllSongByName(@RequestBody String name){
        Iterable<Song> songs = songService.findByName(name);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    @GetMapping("/song/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable("id") Long id){
        Song song = songService.findOneName(id);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }
    @GetMapping("/songs/{id}")
    public ResponseEntity<?> getsongById(@PathVariable Long id){
        Optional<Song> song = songService.findOne(id);
        if(!song.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(song, HttpStatus.OK);
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
    public ResponseEntity<?> getSongListenById(@PathVariable("id") Long id){
        try {
            Song song = songService.findOne(id).orElseThrow(EntityNotFoundException::new);
            song.setNumberOfView(song.getNumberOfView()+1);
            songService.saveSong(song);
            return new ResponseEntity<>(song,HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search-param")
    //singer,String user,String author,String name
    public  ResponseEntity<Iterable<Song>> searchParam(@RequestParam(name = "singer") String singer,
                                                       @RequestParam(name = "user") User user,
                                                       @RequestParam(name = "author") String author,
                                                       @RequestParam(name = "name") String name){
        Iterable<Song> songs = songService.findAllBySingerContainingAndUserContainingAndAuthorContainingAndNameContaining(singer, user, author, name);
        if (songs == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(songs,HttpStatus.OK);
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
