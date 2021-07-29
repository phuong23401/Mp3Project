package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.ERole;
import com.karaoke.mp3project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(ERole name);
}
