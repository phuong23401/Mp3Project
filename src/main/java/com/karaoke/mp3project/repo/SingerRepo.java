package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepo extends JpaRepository<Singer, Long> {
    Iterable<Singer> findAllByNameContaining(String name);
}
