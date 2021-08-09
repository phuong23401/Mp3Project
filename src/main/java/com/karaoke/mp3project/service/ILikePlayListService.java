package com.karaoke.mp3project.service;


import com.karaoke.mp3project.model.CommentPlayList;
import com.karaoke.mp3project.model.LikePlayList;
import com.karaoke.mp3project.model.PlayList;

import java.util.List;

public interface ILikePlayListService {
    Iterable<LikePlayList> findAll();

    Iterable<LikePlayList> findAllByPlaylist(PlayList playlist);

    LikePlayList findOne(Long id);

    void deleteLikeplaylist(Long id);

    void saveLikeplaylist(LikePlayList likeplaylist);

    LikePlayList save(LikePlayList likePlayList);

    List<LikePlayList> findByUserContaining(String username);



}
