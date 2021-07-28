package com.karaoke.mp3project.service;



import com.karaoke.mp3project.model.Singer;

import java.util.ArrayList;

public interface ISingerService {
    ArrayList<Singer> findAll();

    Iterable<Singer> findByName(String name);

    Singer findOne(Long id);

    void saveSinger(Singer singer);
}
