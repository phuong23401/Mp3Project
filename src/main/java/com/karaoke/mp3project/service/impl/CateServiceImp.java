package com.karaoke.mp3project.service.impl;

import com.karaoke.mp3project.model.Category;
import com.karaoke.mp3project.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CateServiceImp  {
    @Autowired
    private CategoryRepo categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }
}
