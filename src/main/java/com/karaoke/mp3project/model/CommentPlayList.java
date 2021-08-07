package com.karaoke.mp3project.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class CommentPlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    private Timestamp createdTime;

    @ManyToOne
    @JoinColumn(name = "playlist")
    private PlayList playlist;

    public CommentPlayList() {
    }

    public CommentPlayList(Long id, String content, User user, PlayList playlist) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.playlist = playlist;
    }

    public CommentPlayList(Long id, String content, User user, Timestamp createdTime, PlayList playlist) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.createdTime = createdTime;
        this.playlist = playlist;
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

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
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
