package com.karaoke.mp3project.service.impl;


import com.karaoke.mp3project.model.LikeSong;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.repo.LikeSongRepo;
import com.karaoke.mp3project.service.ILikesongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeSongService implements ILikesongService {
    @Autowired
    private LikeSongRepo likeSongRepo;

    @Override
    public Iterable<LikeSong> findAll() {
        return likeSongRepo.findAll();
    }

    @Override
    public Iterable<LikeSong> findAllBySong(Song song) {
        return likeSongRepo.findAllBySong(song);
    }
    @Override
    public LikeSong findOne(Long id) {
        return likeSongRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteLikesong(Long id) {
        likeSongRepo.deleteById(id);
    }

    @Override
    public void saveLikesong(LikeSong likesong) {
        likeSongRepo.save(likesong);
    }

    @Override
    public LikeSong save(LikeSong likeSong) {
        return likeSongRepo.save(likeSong);
    }

    @Override
    public List<LikeSong> findByUserContaining(String username) {
        return likeSongRepo.findByUserContaining(username);
    }
}
