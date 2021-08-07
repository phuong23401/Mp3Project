package com.karaoke.mp3project.service;



import com.karaoke.mp3project.model.Singer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ISingerService {
    ArrayList<Singer> findAll();

    Singer findByName(String name);

    Optional<Singer> findOne(Long id);

    Singer saveSinger(Singer singer);

    void deleteSinger(Long id);

    List<Singer> getSingerBySongId(Long id);
}
