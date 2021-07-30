package com.karaoke.mp3project.service;


import com.karaoke.mp3project.model.Singer;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;

import java.util.ArrayList;

public interface ISongService {

    ArrayList<Song> findAll();

    Iterable<Song> findByName(String name);

    Iterable<Song> findByUser(User user);

    Iterable<Song> findBySinger(Singer singer);

    Iterable<Song> findAllByLike();

    Song findOne(Long id);

    void deleteSong(Long id);

    void saveSong(Song song);
}
