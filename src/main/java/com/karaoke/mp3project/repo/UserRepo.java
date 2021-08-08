package com.karaoke.mp3project.repo;

import com.karaoke.mp3project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUsersByUsername(String username);
    Boolean existsUsersByUsername(String username);
    Boolean existsUsersByEmail(String email);
    User findByUsername(String username);
    User findByVerificationCode(String code);

}
