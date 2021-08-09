package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.model.Category;
import com.karaoke.mp3project.service.impl.CateServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/category")
@Repository
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {
    @Autowired
    private CateServiceImp cateServiceImp;

    @GetMapping(value = "categories",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = cateServiceImp.getAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Optional<Category> categories = cateServiceImp.getCategoryById(id);
        if (!categories.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(categories.get(),HttpStatus.OK);
        }
    }
 }
