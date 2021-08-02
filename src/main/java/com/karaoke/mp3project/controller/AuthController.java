package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.request.LoginRequest;
import com.karaoke.mp3project.dto.request.SignupRequest;
import com.karaoke.mp3project.dto.respon.JwtResponse;
import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.ERole;
import com.karaoke.mp3project.model.Role;
import com.karaoke.mp3project.model.Song;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.repo.RoleRepo;
import com.karaoke.mp3project.repo.UserRepo;
import com.karaoke.mp3project.security.jwt.JwtUtils;
import com.karaoke.mp3project.security.userprincipal.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepository;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println("jwt ====> "+jwt);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName()
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> responseEntity(@Valid @RequestBody SignupRequest signupRequest){
        if (userRepo.existsUsersByUsername(signupRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Tài khoản đã tồn tại!"));
        }

        if(userRepo.existsUsersByEmail(signupRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email đã được sử dụng!"));
        }
//        String name, String email, String username, String password, String gender, String hobbies, String avatarUrl) {
        User users = new User(signupRequest.getName(),
                signupRequest.getEmail(),
                signupRequest.getUsername(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getGender(),
                signupRequest.getHobbies(),
                signupRequest.getAvatarUrl()


        );
        System.out.println(signupRequest.getPassword());
        System.out.println(users.getPassword());

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
        userRepo.save(users);
        return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công!"));
    }
}
