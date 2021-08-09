package com.karaoke.mp3project.service.impl;


import com.karaoke.mp3project.dto.request.SignupRequest;
import com.karaoke.mp3project.model.ERole;
import com.karaoke.mp3project.model.Role;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.repo.RoleRepo;
import com.karaoke.mp3project.repo.UserRepo;
import com.karaoke.mp3project.service.IUserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepo roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ArrayList<User> findAll() {
        return (ArrayList<User>) userRepo.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
    @Override
    public User findByVerificationCode(String code) {
        return userRepo.findByVerificationCode(code);
    }

    @Override
    public Boolean verify(String code) {
        User user = findByVerificationCode(code);
        if(user!=null){
            user.setVerifyEmail(true);
            userRepo.save(user);
            return true;
        }else
            return false;
    }

    @Override
    public Boolean check(User user) {
        return user.getVerifyEmail();
    }

    @Override
    public User createNewUser(SignupRequest signupRequest){
        User users = new User();
        users.setName(signupRequest.getName());
        users.setUsername(signupRequest.getUsername());
        users.setEmail(signupRequest.getEmail());
        users.setPassword(encoder.encode(signupRequest.getPassword()));
        users.setVerificationCode(RandomString.make(64));
        users.setAvatarUrl("https://cdn3.vectorstock.com/i/1000x1000/26/62/runner-avatar-figure-with-mp3-player-music-block-vector-32312662.jpg");

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if(strRoles == null){
            Role userRole = roleRepository.findRoleByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            Role adminRole = roleRepository.findRoleByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
        }
        users.setRole(roles);
        return users;
    }
}