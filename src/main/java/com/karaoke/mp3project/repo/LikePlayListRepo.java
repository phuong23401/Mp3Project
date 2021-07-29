package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.LikePlayList;
import com.karaoke.mp3project.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePlayListRepo extends JpaRepository<LikePlayList, Long> {
    Iterable<LikePlayList> findAllByPlaylist(PlayList playlist);
}
