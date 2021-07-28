package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerRepo extends JpaRepository<Singer, Long> {
    Iterable<Singer> findAllByNameContaining(String name);
}
