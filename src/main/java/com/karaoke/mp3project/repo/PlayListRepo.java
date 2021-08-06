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
public interface PlayListRepo extends JpaRepository<PlayList, Long> {
    Iterable<PlayList> findAllByUser(User user);

//    Iterable<PlayList> findAllBySinger(Singer singer);

    Iterable<PlayList> findAllByNameContaining(String name);
//
//    Iterable<PlayList> findAllBySingerContaining(String singer);
//
//    Iterable<PlayList> findAllByAuthorContaining(String author);
//
//    Iterable<PlayList> findAllByUserContaining(String user);

    Iterable<PlayList> findAllByOrderByCountLikeDesc();

    @Query(value = "select * from `play_list` where `play_list`.`name` like ?", nativeQuery = true)
    List<PlayList> findAllByNamePlayList(String namePlayList);

    @Query(value = "select * from `play_list` order by `listen` desc limit 10", nativeQuery = true)
    List<PlayList> findAllOrderByNumberOfListen();

    @Query(value = "select * from `play_list` order by `created_time` desc limit 10", nativeQuery = true)
    List<PlayList> findAllOrderByCreatedTime();

    @Query(value = "select * from `play_list` order by `count_like` desc limit 10", nativeQuery = true)
    List<PlayList> findAllOrderByNumberOfLike();

    @Query(value = "select * from `song` where `song`.`id` in (select `song_id` from `playlist_song` where `playlist_song`.`playlist_id` = ?)", nativeQuery = true)
    List<Song> findAllSongInPlaylist(Long id);



}
