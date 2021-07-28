package com.karaoke.mp3project.model;

import javax.persistence.*;

@Entity
public class LikePlayList {
    @Id
    @GeneratedValue
    private Long id;

    private boolean status = false;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "playlist")
    private PlayList playlist;

    public LikePlayList() {
    }

    public LikePlayList(Long id, boolean status, User user, PlayList playlist) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlayList getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlayList playlist) {
        this.playlist = playlist;
    }
}
