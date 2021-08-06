package com.karaoke.mp3project.service.impl;

import com.karaoke.mp3project.model.LikePlayList;
import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.repo.LikePlayListRepo;
import com.karaoke.mp3project.service.ILikePlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikePlayListService implements ILikePlayListService {
    @Autowired
    private LikePlayListRepo likePlayListRepo;

    @Override
    public Iterable<LikePlayList> findAll() {
        return likePlayListRepo.findAll();
    }

    @Override
    public Iterable<LikePlayList> findAllByPlaylist(PlayList playlist) {
        return likePlayListRepo.findAllByPlaylist(playlist);
    }

    @Override
    public LikePlayList findOne(Long id) {
        return likePlayListRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteLikeplaylist(Long id) {
        likePlayListRepo.deleteById(id);
    }

    @Override
    public void saveLikeplaylist(LikePlayList likeplaylist) {
        likePlayListRepo.save(likeplaylist);
    }

    @Override
    public LikePlayList save(LikePlayList likePlayList) {
        return likePlayListRepo.save(likePlayList);
    }

    @Override
    public List<LikePlayList> findByUserContaining(String username) {
        return likePlayListRepo.findByUserContaining(username);
    }
}
