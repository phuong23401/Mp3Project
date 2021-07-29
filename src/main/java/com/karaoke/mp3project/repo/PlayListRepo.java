package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.PlayList;
import com.karaoke.mp3project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepo extends JpaRepository<PlayList, Long> {
    Iterable<PlayList> findAllByUser(User user);

    Iterable<PlayList> findAllByNameContaining(String name);
}
