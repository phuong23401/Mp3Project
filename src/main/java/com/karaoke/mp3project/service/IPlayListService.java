package com.karaoke.mp3project.service;



import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.model.User;

import java.util.ArrayList;
import java.util.Optional;

public interface IPlayListService {
    ArrayList<PlayList> findAll();

    Iterable<PlayList> findByUser(User user);

    Iterable<PlayList> findByName(String name);

    PlayList findOne(Long id);

    void deletePlaylist(Long id);

    void savePlaylist(PlayList playlist);
    Optional<PlayList> findById(Long id);
}
