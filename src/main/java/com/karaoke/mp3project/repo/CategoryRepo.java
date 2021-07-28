package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
