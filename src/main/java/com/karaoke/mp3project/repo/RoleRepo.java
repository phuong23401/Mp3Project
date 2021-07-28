package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.ERole;
import com.karaoke.mp3project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(ERole name);
}
