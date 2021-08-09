package com.karaoke.mp3project.service.impl;


import com.karaoke.mp3project.model.AddSongToPlaylistReq;
import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.repo.PlayListRepo;
import com.karaoke.mp3project.repo.SongRepo;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.IPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayListService implements IPlayListService {

    @Autowired
    private SongRepo songRepo;

    @Autowired
    private PlayListRepo playListRepo;

    @Override
    public ArrayList<PlayList> findAll() {
        return (ArrayList<PlayList>) playListRepo.findAll();
    }

    @Override
    public List<PlayList> findByUser(User user) {
        return playListRepo.findAllByUser(user);
    }

    @Override
    public Iterable<PlayList> findByName(String name) {
        return playListRepo.findAllByNameContaining(name);
    }

    @Override
    public Optional<PlayList> findOne(Long id) {
        return playListRepo.findById(id);
    }

    @Autowired
    private UserDtService userDtService;

    @Override
    public void deletePlaylist(Long id) {
        playListRepo.deleteById(id);
    }

    @Override
    public void savePlaylist(PlayList playlist) {
        User userCurrent = userDtService.getCurrentUser();
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
        Long listenNum = Long.valueOf(0);
        playlist.setCreatedTime(createdTime);
        playlist.setUpdatedTime(updatedTime);
        playlist.setListen(listenNum);
        playlist.setUser(userCurrent);
        playListRepo.save(playlist);
    }
    @Override
    public void editPlaylist(Long id, PlayList newPlaylist){
        User user = userDtService.getCurrentUser();
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
        Long listenNum = Long.valueOf(0);
        newPlaylist.setCreatedTime(createdTime);
        newPlaylist.setUpdatedTime(updatedTime);
        newPlaylist.setListen(listenNum);
        newPlaylist.setId(id);
        newPlaylist.setUser(user);
        playListRepo.save(newPlaylist);
    }

    @Override
    public Optional<PlayList> findById(Long id) {
        return playListRepo.findById(id);
    }

    @Override
    public List<PlayList> findAllByNamePlayList(String namePlaylist) {
        return playListRepo.findAllByNamePlayList(namePlaylist);
    }

    @Override
    public List<PlayList> findAllByOrderByCreationTime() {
        return playListRepo.findAllOrderByCreatedTime();
    }

    @Override
    public List<PlayList> findAllOrderByNumberOfListen() {
        return playListRepo.findAllOrderByNumberOfListen();
    }

    @Override
    public List<PlayList> findAllOrderByNumberOfLike() {
        return playListRepo.findAllOrderByNumberOfLike();
    }

    @Override
    public List<Song> findAllSongInPlaylist(Long id) {
        Optional<PlayList> playList = playListRepo.findById(id);
        return songRepo.findSongByPlaylist(playList.get());
    }

    @Override
    public Iterable<Number> countSongInPlaylist(Long id) {
        return playListRepo.countSongInPlaylist(id);
    }

    @Override
    public PlayList findOnePlayList(Long id) {
        return playListRepo.findById(id).orElse(null);
    }

    @Override
    public void addSongToPlaylist(Song song, PlayList playList) {
        List<Song> songs = playList.getSongs();
        songs.add(song);
        playList.setSongs(songs);
        playListRepo.save(playList);
    }
}
