package com.karaoke.mp3project.service;



import com.karaoke.mp3project.model.Singer;

import java.util.ArrayList;
import java.util.Optional;

public interface ISingerService {
    ArrayList<Singer> findAll();

    Iterable<Singer> findByName(String name);

    Optional<Singer> findOne(Long id);

    void saveSinger(Singer singer);
    void deleteSinger(Long id);
}
