package com.karaoke.mp3project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Vui lòng nhập tên bài hát!")
    @Size(max= 100, message = "Vui lòng nhập đúng tên bài hát!")
    private String name;

    private String description;

    @Size(max = 50, message = "Vui lòng không spam!")
    private String tags;
    private String avatarUrl;
    private String fileUrl;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private Long countLike = 0L;
    private String lyric;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "singer_song",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "singer_id")})
    @JsonIgnoreProperties("songs")
    private Set<Singer> singer;

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "playlist_song",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "playlist_id")})
    @JsonIgnoreProperties("songs")
    private Set<PlayList> playlist;

    public Song() {
    }

    public Song(Long id, String name, String description, String tags, String avatarUrl, String fileUrl, Timestamp createdTime, Timestamp updatedTime, Long countLike, Category category, Set<Singer> singer, Set<PlayList> playlist) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.avatarUrl = avatarUrl;
        this.fileUrl = fileUrl;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.countLike = countLike;
        this.category = category;
        this.singer = singer;
        this.playlist = playlist;
    }

    public Song(Long id, String name, String description, String tags, String avatarUrl, String fileUrl, Timestamp createdTime, Timestamp updatedTime, Long countLike, String lyric, User user, Category category, Set<Singer> singer, Set<PlayList> playlist) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.avatarUrl = avatarUrl;
        this.fileUrl = fileUrl;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.countLike = countLike;
        this.lyric = lyric;
        this.user = user;
        this.category = category;
        this.singer = singer;
        this.playlist = playlist;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Singer> getSinger() {
        return singer;
    }

    public void setSinger(Set<Singer> singer) {
        this.singer = singer;
    }

    public Set<PlayList> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Set<PlayList> playlist) {
        this.playlist = playlist;
    }
}
