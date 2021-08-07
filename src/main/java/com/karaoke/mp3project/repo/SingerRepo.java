package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerRepo extends JpaRepository<Singer, Long> {
    Singer findAllByName(String name);

    @Query(value = "select * from `singer` where `singer`.`id` in (select `singer_id` from `singer_song` where `singer_song`.`song_id` = ?)", nativeQuery = true)
    List<Singer> getSingerBySongId(Long id);
}
