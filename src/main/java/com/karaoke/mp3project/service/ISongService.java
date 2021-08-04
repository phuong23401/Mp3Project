package com.karaoke.mp3project.service;


import com.karaoke.mp3project.model.Singer;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ISongService {
    Page<Song> findAllSong(Pageable pageable);

    ArrayList<Song> findAll();

    Iterable<Song> findByName(String name);

    Iterable<Song> findByUser(User user);

    Iterable<Song> findBySinger(Singer singer);

    Iterable<Song> findAllByLike();

    Optional<Song> findOne(Long id);
    Song findOneName(Long id);
    void deleteSong(Long id);
    void saveSong(Song song);
    List<Song> findAllByNameSong(String nameSong);
    List<Song> findAllByCreationTimeOrderByCreationTime();


}
