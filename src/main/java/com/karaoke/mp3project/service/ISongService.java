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

    List<Song> findAll();

    Iterable<Song> findByName(String name);

    Iterable<Song> findByUser(User user);

    Iterable<Song> findBySinger(Singer singer);

    List<Song> findAllByLike();

    Song findById(Long id);

    List<Song> findSongByUser(Long id);

    Optional<Song> findOne(Long id);

    Song findOneName(Long id);

    void deleteSong(Long id);

    void saveSong(Song song);

    List<Song> findAllByNameSong(String nameSong);

    List<Song> findAllByCreationTimeOrderByCreationTime();

    Iterable<Song> findAllBySingerContainingAndUserContainingAndAuthorContainingAndNameContaining(String singer,User user,String author,String name);

    Iterable<Song> findAllBySingerContaining(String singer);

    Iterable<Song> findAllByAuthorContaining(String author);

    Iterable<Song> findAllByUserContaining(String user);

    void addSong(Song song);

    void editSong(Long id, Song newSong);


}
