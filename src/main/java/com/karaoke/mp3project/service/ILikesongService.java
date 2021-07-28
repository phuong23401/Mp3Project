package com.karaoke.mp3project.service;


import com.karaoke.mp3project.model.LikeSong;
import com.karaoke.mp3project.model.Song;

public interface ILikesongService {
    Iterable<LikeSong> findAll();

    Iterable<LikeSong> findAllBySong(Song song);

    LikeSong findOne(Long id);

    void deleteLikesong(Long id);

    void saveLikesong(LikeSong likesong);
}
