package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.Singer;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {
    Iterable<Song> findAllByUser(User user);

    Iterable<Song> findAllBySinger(Singer singer);

    Iterable<Song> findAllByNameContaining(String name);

    Iterable<Song> findAllByOrderByCountLikeDesc();
    @Query(value = "select * from song where song.name like ?", nativeQuery = true)
    List<Song> findAllByNameSong(String nameSong);
//2 luot nghe nhieu nhat
    @Query(value = "select * from song order by number_of_view desc limit 2", nativeQuery = true)
    List<Song> findAllByNumberOfViewOrderByNumberOfView();

}
