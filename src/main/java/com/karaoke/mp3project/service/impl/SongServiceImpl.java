package com.karaoke.mp3project.service.impl;

import com.karaoke.mp3project.model.Singer;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.repo.SongRepo;
import com.karaoke.mp3project.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SongServiceImpl implements ISongService {

    @Autowired
    private SongRepo songRepo;

    @Override
    public ArrayList<Song> findAll() {
        return (ArrayList<Song>) songRepo.findAll();
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
    public Song findOne(Long id) {
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
}
