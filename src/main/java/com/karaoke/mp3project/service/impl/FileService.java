package com.karaoke.mp3project.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;


@Service
public class FileService {
    @Autowired
    private SongRepo songRepo;

    public List<Song> getEmployeeList() {

        List<Song> list = songRepo.findAll();
        return list.stream().map(emp -> {
            Song song = new Song();
            song.setId(emp.getId());
            song.setName(emp.getName());
            song.setFileUrl(emp.getFileUrl());
            return song;
        }).collect(Collectors.toList());

    }
}
