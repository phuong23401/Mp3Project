package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.LikeSong;
import com.karaoke.mp3project.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeSongRepo extends JpaRepository<LikeSong, Long> {
    Iterable<LikeSong> findAllBySong(Song song);
}
