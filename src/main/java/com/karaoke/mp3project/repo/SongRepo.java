package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.PlayList;
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

    @Query(value = "select * from song where song.user_id = ?", nativeQuery = true)
    List<Song> findSongByUser(Long id);


    Iterable<Song> findAllBySinger(Singer singer);

    Iterable<Song> findAllByNameContaining(String name);

    Iterable<Song> findAllBySingerContaining(String singer);

    Iterable<Song> findAllByAuthorContaining(String author);

    Iterable<Song> findAllByUserContaining(String user);



    List<Song> findAllByOrderByCountLikeDesc();
    @Query(value = "select * from song where song.name like ?", nativeQuery = true)
    List<Song> findAllByNameSong(String nameSong);
//2 luot nghe nhieu nhat
    @Query(value = "select * from song order by number_of_view desc limit 4", nativeQuery = true)
    List<Song> findAllByNumberOfViewOrderByNumberOfView();

    Iterable<Song> findAllBySingerContainingAndUserContainingAndAuthorContainingAndNameContaining(String singer,User user,String author,String name);

    List<Song> findSongByPlaylist(PlayList playList);

}
