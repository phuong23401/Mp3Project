package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.model.Singer;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListRepo extends JpaRepository<PlayList, Long> {
    List<PlayList> findAllByUser(User user);


    Iterable<PlayList> findAllByNameContaining(String name);

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

    @Query(value = "select count(`song`.id) from song where song.id in (select `song_id` from `playlist_song` where `playlist_song`.`playlist_id` = ?)", nativeQuery = true)
    Iterable<Number> countSongInPlaylist(Long id);

    @Query(value ="delete from `playlist_song` where 'song_id' = ?", nativeQuery = true)
    void deleteSongInThePlaylist(Long id);
}
