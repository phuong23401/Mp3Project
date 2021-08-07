package com.karaoke.mp3project.service.impl;


import com.karaoke.mp3project.model.Singer;
import com.karaoke.mp3project.repo.SingerRepo;
import com.karaoke.mp3project.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SingerService implements ISingerService {

    @Autowired
    private SingerRepo singerRepo;

    @Override
    public ArrayList<Singer> findAll() {
        return (ArrayList<Singer>) singerRepo.findAll();
    }

    @Override
    public Singer findByName(String name) {
        return singerRepo.findAllByName(name);
    }

    @Override
    public Optional<Singer> findOne(Long id) {
        return singerRepo.findById(id);
    }

    @Override
    public Singer saveSinger(Singer singer) {
       return singerRepo.save(singer);
    }

    @Override
    public void deleteSinger(Long id) {
        singerRepo.deleteById(id);
    }

    @Override
    public List<Singer> getSingerBySongId(Long id) {
        return singerRepo.getSingerBySongId(id);
    }
}
