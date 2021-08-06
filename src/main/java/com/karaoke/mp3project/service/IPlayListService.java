package com.karaoke.mp3project.service;



import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IPlayListService {
    ArrayList<PlayList> findAll();

    Iterable<PlayList> findByUser(User user);

    Iterable<PlayList> findByName(String name);

    Optional findOne(Long id);

    void deletePlaylist(Long id);

    void savePlaylist(PlayList playlist);

    Optional findById(Long id);

    List<PlayList> findAllByNamePlayList(String namePlayList);

    List<PlayList> findAllByOrderByCreationTime();

    List<PlayList> findAllOrderByNumberOfListen();

    List<PlayList> findAllOrderByNumberOfLike();

    List<Song> findAllSongInPlaylist(Long id);
}
