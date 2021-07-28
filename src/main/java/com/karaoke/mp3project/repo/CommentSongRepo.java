package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.CommentSong;
import com.karaoke.mp3project.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentSongRepo extends JpaRepository<CommentSong, Long> {
    Iterable<CommentSong> findAllBySong(Song song);
}
