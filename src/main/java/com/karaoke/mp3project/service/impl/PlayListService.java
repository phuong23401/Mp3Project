package com.karaoke.mp3project.service.impl;


import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.repo.PlayListRepo;
import com.karaoke.mp3project.service.IPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PlayListService implements IPlayListService {

    @Autowired
    private PlayListRepo playListRepo;

    @Override
    public ArrayList<PlayList> findAll() {
        return (ArrayList<PlayList>) playListRepo.findAll();
    }

    @Override
    public Iterable<PlayList> findByUser(User user) {
        return playListRepo.findAllByUser(user);
    }

    @Override
    public Iterable<PlayList> findByName(String name) {
        return playListRepo.findAllByNameContaining(name);
    }

    @Override
    public PlayList findOne(Long id) {
        return playListRepo.findById(id).orElse(null);
    }

    @Override
    public void deletePlaylist(Long id) {
        playListRepo.deleteById(id);
    }

    @Override
    public void savePlaylist(PlayList playlist) {
        playListRepo.save(playlist);
    }

    @Override
    public Optional<PlayList> findById(Long id) {
        return playListRepo.findById(id);
    }
}
