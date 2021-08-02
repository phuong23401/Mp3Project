package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckRepository extends JpaRepository<Check, Long> {
}
