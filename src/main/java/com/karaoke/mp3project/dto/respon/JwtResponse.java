package com.karaoke.mp3project.dto.respon;

import com.karaoke.mp3project.model.User;

public class JwtResponse {
    private String token;
    private Long id;
    private User user;

    public JwtResponse(String token, Long id, User user) {
        this.token = token;
        this.id = id;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
