package com.karaoke.mp3project.model;

import javax.persistence.*;

@Entity
public class LikeSong {
    @Id
    @GeneratedValue
    private Long id;

    private boolean status = false;

    private String user;

    private String song;

    public LikeSong() {
    }


    public LikeSong(Long id, boolean status, String user, String song) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.song = song;
    }

    public LikeSong(Long id, String user, String song) {
        this.id = id;
        this.user = user;
        this.song = song;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
