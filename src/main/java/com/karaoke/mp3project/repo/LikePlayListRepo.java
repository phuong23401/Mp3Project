package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.LikePlayList;
import com.karaoke.mp3project.model.LikeSong;
import com.karaoke.mp3project.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikePlayListRepo extends JpaRepository<LikePlayList, Long> {
    Iterable<LikePlayList> findAllByPlaylist(PlayList playlist);

    List<LikePlayList> findByUserContaining(String username);
}
