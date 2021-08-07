package com.karaoke.mp3project.service;


import com.karaoke.mp3project.model.LikeSong;
import com.karaoke.mp3project.model.Song;

import java.util.List;

public interface ILikesongService {
    Iterable<LikeSong> findAll();

    Iterable<LikeSong> findAllBySong(Song song);

    LikeSong findOne(Long id);

    void deleteLikesong(Long id);

    void saveLikesong(LikeSong likesong);

    LikeSong save(LikeSong likeSong);

    List<LikeSong> findByUserContaining(String username);
}
