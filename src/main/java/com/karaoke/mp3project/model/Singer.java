package com.karaoke.mp3project.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String avatarUrl;

//    @ManyToMany(mappedBy = "singer")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
////    @JoinTable(name = "singer_song",
////            joinColumns = {@JoinColumn(name = "singer_id")},
////            inverseJoinColumns = {@JoinColumn(name = "song_id")})
//    @JsonIgnoreProperties("singers")
//    private Collection<Song> songs;

    public Singer() {
    }

    public Singer(Long id, String name, String description, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatarUrl = avatarUrl;
//        this.songs = songs;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

//    public Collection<Song> getSongs() {
//        return songs;
//    }
//
//    public void setSongs(Collection<Song> songs) {
//        this.songs = songs;
//    }
}
