package com.karaoke.mp3project.service.impl;


import com.karaoke.mp3project.model.Singer;
import com.karaoke.mp3project.repo.SingerRepo;
import com.karaoke.mp3project.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SingerServiceImpl implements ISingerService {

    @Autowired
    private SingerRepo singerRepo;

    @Override
    public ArrayList<Singer> findAll() {
        return (ArrayList<Singer>) singerRepo.findAll();
    }

    @Override
    public Iterable<Singer> findByName(String name) {
        return singerRepo.findAllByNameContaining(name);
    }

    @Override
    public Singer findOne(Long id) {
        return singerRepo.findById(id).orElse(null);
    }

    @Override
    public void saveSinger(Singer singer) {
        singerRepo.save(singer);
    }
}
