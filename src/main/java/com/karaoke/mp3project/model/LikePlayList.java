package com.karaoke.mp3project.model;

import javax.persistence.*;

@Entity
public class LikePlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status = false;

    private String user;

    private String playlist;

    public LikePlayList() {
    }

    public LikePlayList(Long id, boolean status, String user, String playlist) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.playlist = playlist;
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

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }
}
