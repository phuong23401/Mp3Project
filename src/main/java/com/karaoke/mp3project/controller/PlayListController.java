package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.*;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.impl.LikePlayListService;
import com.karaoke.mp3project.service.impl.PlayListService;
import com.karaoke.mp3project.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/playlist")
@RestController
public class PlayListController {
    @Autowired
    private PlayListService playlistService;

    @Autowired
    private LikePlayListService likePlayListService;

    @Autowired
    private UserDtService userDtService;

    @Autowired
    private SongService songService;


    @PostMapping("/create")
    public ResponseEntity<?> createPlaylist(@Valid @RequestBody PlayList playlist) {
        if (playlist.getAvatarUrl() == null || playlist.getAvatarUrl().trim().isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("No Avatar"), HttpStatus.BAD_REQUEST);
        }
        playlistService.savePlaylist(playlist);
        return new ResponseEntity<>(new MessageResponse("Create playlist successfully !"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPlaylist(@PathVariable Long id, @Valid @RequestBody PlayList newPlaylist) {
        Optional<PlayList> playList = playlistService.findOne(id);
        User user = userDtService.getCurrentUser();
        if (!playList.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (newPlaylist.getAvatarUrl() == null || newPlaylist.getAvatarUrl().trim().isEmpty()) {
                return new ResponseEntity<>(new MessageResponse("Novatar"), HttpStatus.OK);
            }
            newPlaylist.setUser(user);
            playlistService.editPlaylist(id, newPlaylist);
            return new ResponseEntity<>(new MessageResponse("Successfully"), HttpStatus.OK);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deletePlaylist(@RequestBody Long id) {
        Optional<PlayList> playlist = playlistService.findOne(id);
        if (!playlist.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlist.get().setUser(null);
        playlistService.deletePlaylist(playlist.get().getId());
        return new ResponseEntity<>(new MessageResponse("Successfully!"), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getPlaylistByUser() {
        User userCurrent = userDtService.getCurrentUser();
        List<PlayList> playlist = playlistService.findByUser(userCurrent);
        if (playlist.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }





    @PutMapping("/update")
    public ResponseEntity<MessageResponse> editPlaylist(@RequestBody PlayList playlist) {
        playlistService.savePlaylist(playlist);
        String message = "Update playlist successfully!";
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @PutMapping("/addsong")
    public ResponseEntity<MessageResponse> addSongToPlaylist(@RequestBody AddSongToPlaylistReq addSongToPlaylistReq) {
        Song song = songService.findById(Long.parseLong(addSongToPlaylistReq.getIdSong()));
        Optional<PlayList> playList =playlistService.findOne(Long.parseLong(addSongToPlaylistReq.getIdPlaylist()));
        playlistService.addSongToPlaylist(song,playList.get());
        String message = "Add playlist successfully!";
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

}
