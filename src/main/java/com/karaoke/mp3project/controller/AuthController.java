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
import com.karaoke.mp3project.service.IEmailSender;
import com.karaoke.mp3project.service.IUserService;
import com.karaoke.mp3project.service.IUtility;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
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

    @Autowired
    IUtility utility;

    @Autowired
    IEmailSender emailSender;

    @Autowired
    IUserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        if(!userService.check(userService.findByUsername(userDetails.getUsername()))){
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "Unverified account"));
        }
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername()
        ));
    }

    @PostMapping(value = "/signup",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> responseEntity(@Valid @RequestBody SignupRequest signupRequest, HttpServletRequest request){
        String siteUrl = utility.getSiteURL(request);
        if (userRepo.existsUsersByUsername(signupRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username is existed !"));
        }
        if(userRepo.existsUsersByEmail(signupRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email is existed!"));
        }
        User users = userService.createNewUser(signupRequest);
        userRepo.save(users);
        try {
            emailSender.send(users, siteUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new  ResponseEntity<>(new MessageResponse("Register successfully"), HttpStatus.OK);
    }
}