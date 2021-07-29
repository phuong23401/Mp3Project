package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.Singer;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {
    Iterable<Song> findAllByUser(User user);

    Iterable<Song> findAllBySinger(Singer singer);

    Iterable<Song> findAllByNameContaining(String name);

    Iterable<Song> findAllByOrderByCountLikeDesc();
}
