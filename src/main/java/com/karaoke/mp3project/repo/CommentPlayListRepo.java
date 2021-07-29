package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.CommentPlayList;
import com.karaoke.mp3project.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentPlayListRepo extends JpaRepository<CommentPlayList, Long> {
    Iterable<CommentPlayList> findAllByPlaylist(PlayList playlist);
}
