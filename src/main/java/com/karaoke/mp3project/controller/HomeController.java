package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.*;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.ICommentPlayListService;
import com.karaoke.mp3project.service.ICommentSongService;
import com.karaoke.mp3project.service.impl.*;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.impl.LikeSongService;
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

    @Autowired
    private PlayListService playlistService;

    @Autowired
    private LikePlayListService likePlayListService;

    @Autowired
    private ICommentSongService commentsongService;

    @Autowired
    ICommentPlayListService commentPlayListService;
    @Autowired
    private PlayListService playListService;

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


    @GetMapping("/countLikedPlaylist/{id}")
    public ResponseEntity<?> countLikedPlaylist(@PathVariable Long id) {
        try {
            PlayList playList = playlistService.findOne(id).orElseThrow(EntityNotFoundException::new);
            User user =  userDtService.getCurrentUser();
            List<LikePlayList> likePlayLists = likePlayListService.findByUserContaining(user.getUsername());
            if (likePlayLists.size() != 0){
                for (int i = 0; i < likePlayLists.size(); i++) {
                    if (likePlayLists.get(i).getPlaylist().equals(playList.getName())){
                        likePlayListService.deleteLikeplaylist(likePlayLists.get(i).getId());
                        playList.setCountLike(playList.getCountLike() -1);
                        playlistService.savePlaylist(playList);
                        return  new ResponseEntity<>(playList,HttpStatus.OK);
                    }
                }
            }
            LikePlayList likePlayList = new LikePlayList();
            likePlayList.setPlaylist(playList.getName());
            likePlayList.setUser(user.getUsername());
            likePlayListService.save(likePlayList);
            playList.setCountLike(playList.getCountLike() +1);
            playlistService.savePlaylist(playList);
            return new ResponseEntity<>(playList,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(new MessageResponse(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/countListenPlaylist/{id}")
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

    @GetMapping("/topListenedPlaylist")
    public ResponseEntity<List<PlayList>> getTopListened() {
        List<PlayList> playlists = playlistService.findAllOrderByNumberOfListen();
        if (playlists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/newlestCreatedPlaylist")
    public ResponseEntity<List<PlayList>> getNewlestCreated() {
        List<PlayList> playlists = playlistService.findAllByOrderByCreationTime();
        if (playlists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/topLikedPlaylist")
    public ResponseEntity<List<PlayList>> getTopLiked() {
        List<PlayList> playlists = playlistService.findAllOrderByNumberOfLike();
        if (playlists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PlayList>> getAllPlaylist() {
        List<PlayList> playlists = playlistService.findAll();
        if (playlists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/comment-song/{id}")
    public ResponseEntity<Iterable<CommentSong>> getAllCommentBySong(@PathVariable("id") Long id){
        Song song = songService.findById(id);
        Iterable<CommentSong> comments = commentsongService.findAllBySong(song);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @GetMapping("/comment-play-list/{id}")
    public ResponseEntity<Iterable<CommentPlayList>> getAllCommentByPlaylist(@PathVariable("id") Long id){
        PlayList playlist = playlistService.findOnePlayList(id);
        Iterable<CommentPlayList> comments = commentPlayListService.findAllByPlaylist(playlist);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @GetMapping("/playlist/{id}")
    public ResponseEntity<PlayList> getPlaylistById(@PathVariable("id") Long id) {
        PlayList playlist = playlistService.findOnePlayList(id);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

}
