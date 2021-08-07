package com.karaoke.mp3project.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class CommentSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "song")
    private Song song;
    private Timestamp createdTime;

    public CommentSong() {
    }

    public CommentSong(Long id, String content, User user, Song song) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.song = song;
    }

    public CommentSong(Long id, String content, User user, Song song, Timestamp createdTime) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.song = song;
        this.createdTime = createdTime;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
