package com.karaoke.mp3project.dto.respon;

import com.karaoke.mp3project.model.User;

public class JwtResponse {
    private String token;
    private Long id;
    private String name;

    public JwtResponse(String token, Long id, String name) {
        this.token = token;
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
