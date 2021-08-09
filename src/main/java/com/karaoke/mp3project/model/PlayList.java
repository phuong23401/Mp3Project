package com.karaoke.mp3project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Vui lòng nhập tên playlist của bạn!")
    private String name;
    private String avatarUrl;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private Long countLike = 0L;
    private Long listen;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "playlist_song",
            joinColumns = {@JoinColumn(name = "playlist_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id")})
    @JsonIgnoreProperties("playlists")
    private List<Song> songs;

    public PlayList() {
    }

    public PlayList(Long id, String name, String avatarUrl, Long countLike, Long listen, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.countLike = countLike;
        this.listen = listen;
        this.songs = songs;
    }

    public PlayList(Long id, String name, String avatarUrl, Timestamp createdTime, Timestamp updatedTime, Long countLike, Long listen, User user) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.countLike = countLike;
        this.listen = listen;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getCountLike() {
        return countLike;
    }

    public void setCountLike(Long countLike) {
        this.countLike = countLike;
    }

    public Long getListen() {
        return listen;
    }

    public void setListen(Long listen) {
        this.listen = listen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
