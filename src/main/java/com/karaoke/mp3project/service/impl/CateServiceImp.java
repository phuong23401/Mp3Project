package com.karaoke.mp3project.service.impl;

import com.karaoke.mp3project.model.Category;
import com.karaoke.mp3project.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateServiceImp  {
    @Autowired
    private CategoryRepo categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
}
