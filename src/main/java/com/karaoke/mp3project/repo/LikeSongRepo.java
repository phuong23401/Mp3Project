package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.LikeSong;
import com.karaoke.mp3project.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeSongRepo extends JpaRepository<LikeSong, Long> {
    Iterable<LikeSong> findAllBySong(Song song);

    List<LikeSong> findByUserContaining(String username);
}
