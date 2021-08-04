package com.karaoke.mp3project.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "checkkk")
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    private User user;


}
