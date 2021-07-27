package com.karaoke.mp3project.model;

import javax.persistence.*;

@Entity
public class LikeSong {
    @Id
    @GeneratedValue
    private Long id;

    private boolean status = false;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "song")
    private Song song;

    public LikeSong() {
    }

    public LikeSong(Long id, boolean status, User user, Song song) {
        this.id = id;
        this.status = status;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
