package com.karaoke.mp3project.security.userprincipal;

import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDtService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Goi ham loadUssẻ");
        System.out.println("username = "+username);
        User user = userRepo.findUsersByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetails.build(user);
    }

    public User getCurrentUser(){
        Optional<User> user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();;
        if(principal instanceof UserDetails){
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        if(userRepo.existsUsersByUsername(userName)){
            user = userRepo.findUsersByUsername(userName);
        } else {
            user = Optional.of(new User());
            user.get().setUsername("Anonymous");
        }
        return user.get();
    }
}
