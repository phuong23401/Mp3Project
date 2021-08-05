package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.impl.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/playlist")
@RestController
public class PlayListController {
    @Autowired
    private PlayListService playlistService;

    @Autowired
    private UserDtService userDtService;

    @GetMapping("/getAll")
    public ResponseEntity<List<PlayList>> getAllPlaylist() {
        List<PlayList> playlists = playlistService.findAll();
        if (playlists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPlaylist(@Valid @RequestBody PlayList playlist) {
        if (playlist.getAvatarUrl() == null || playlist.getAvatarUrl().trim().isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("No Avatar"), HttpStatus.OK);
        }
        User userCurrent = userDtService.getCurrentUser();
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
        Long listenNum = Long.valueOf(0);

        playlist.setCreatedTime(createdTime);
        playlist.setUpdatedTime(updatedTime);
        playlist.setListen(listenNum);
        playlist.setUser(userCurrent);
        playlistService.savePlaylist(playlist);
        return new ResponseEntity<>(new MessageResponse("Create playlist successfully !"), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editPlaylist(@PathVariable Long id, @Valid @RequestBody PlayList newPlaylist) {
        Optional<PlayList> playList = playlistService.findOne(id);
        if (!playList.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (newPlaylist.getAvatarUrl() == null || newPlaylist.getAvatarUrl().trim().isEmpty()) {
                return new ResponseEntity<>(new MessageResponse("No Avatar"), HttpStatus.OK);
            }
            Timestamp createdTime = new Timestamp(System.currentTimeMillis());
            Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
            Long listenNum = Long.valueOf(0);

            newPlaylist.setCreatedTime(createdTime);
            newPlaylist.setUpdatedTime(updatedTime);
            newPlaylist.setListen(listenNum);
            newPlaylist.setId(id);
            playlistService.savePlaylist(newPlaylist);
            return new ResponseEntity<>(new MessageResponse("Update playlist successfully !"), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id) {
        Optional<PlayList> playlist = playlistService.findOne(id);
        if (!playlist.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlistService.deletePlaylist(playlist.get().getId());
        return new ResponseEntity<>(new MessageResponse("Delete playlist successfully !"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylistById(@PathVariable Long id) {
        Optional<PlayList> playlist = playlistService.findOne(id);
        if (!playlist.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @GetMapping("/topListened")
    public ResponseEntity<List<PlayList>> getTopListened() {
        List<PlayList> playlists = playlistService.findAllOrderByNumberOfListen();
        if (playlists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/newlestCreated")
    public ResponseEntity<List<PlayList>> getNewlestCreated() {
        List<PlayList> playlists = playlistService.findAllByOrderByCreationTime();
        if (playlists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/topLiked")
    public ResponseEntity<List<PlayList>> getTopLiked() {
        List<PlayList> playlists = playlistService.findAllOrderByNumberOfLike();
        if (playlists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/countListen/{id}")
    public ResponseEntity<?> countListenById(@PathVariable Long id) {
        try {
            PlayList playlist = playlistService.findOne(id).orElseThrow(EntityNotFoundException::new);
            playlist.setListen(playlist.getListen() + 1);
            playlistService.savePlaylist(playlist);
            return new ResponseEntity<>(playlist, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
