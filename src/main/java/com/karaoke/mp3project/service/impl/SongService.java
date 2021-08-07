package com.karaoke.mp3project.service.impl;

import com.karaoke.mp3project.model.Singer;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.repo.SongRepo;
import com.karaoke.mp3project.security.userprincipal.UserDtService;
import com.karaoke.mp3project.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongService implements ISongService {

    @Autowired
    private SongRepo songRepo;

    @Autowired
    UserDtService userDtService;

    @Override
    public Page<Song> findAllSong(Pageable pageable) {
        return songRepo.findAll(pageable);
    }

    @Override
    public List<Song> findAll() {
        return  songRepo.findAll();
    }

    @Override
    public List<Song> findSongByUser(Long id) {
        return songRepo.findSongByUser(id);
    }

    @Override
    public Iterable<Song> findByName(String name) {
        return songRepo.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Song> findByUser(User user) {
        return songRepo.findAllByUser(user);
    }

    @Override
    public Iterable<Song> findBySinger(Singer singer) {
        return songRepo.findAllBySinger(singer);
    }

    @Override
    public Iterable<Song> findAllByLike() {
        return songRepo.findAllByOrderByCountLikeDesc();
    }

    @Override
    public Optional<Song> findOne(Long id) {
        return songRepo.findById(id);
    }

    @Override
    public Song findOneName(Long id) {
        return songRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteSong(Long id) {
        songRepo.deleteById(id);
    }

    @Override
    public void saveSong(Song song) {
        songRepo.save(song);
    }

    @Override
    public List<Song> findAllByNameSong(String nameSong) {
        return songRepo.findAllByNameSong(nameSong);
    }

    @Override
    public List<Song> findAllByCreationTimeOrderByCreationTime() {
        return songRepo.findAllByNumberOfViewOrderByNumberOfView();
    }

    @Override
    public Iterable<Song> findAllBySingerContainingAndUserContainingAndAuthorContainingAndNameContaining(String singer, User user, String author, String name) {
        return songRepo.findAllBySingerContainingAndUserContainingAndAuthorContainingAndNameContaining(singer, user, author, name);
    }

    @Override
    public Iterable<Song> findAllBySingerContaining(String singer) {
        return songRepo.findAllBySingerContaining(singer);
    }

    @Override
    public Iterable<Song> findAllByAuthorContaining(String author) {
        return songRepo.findAllByAuthorContaining(author);
    }

    @Override
    public Iterable<Song> findAllByUserContaining(String user) {
        return songRepo.findAllByUserContaining(user);
    }
}
