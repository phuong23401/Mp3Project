package com.karaoke.mp3project.model;

public class UserCurrentUpdate {
    private String name;
    private String gender;
    private String hobbies;
    private String avatarUrl;

    public UserCurrentUpdate() {
    }

    public UserCurrentUpdate(String name, String gender, String hobbies, String avatarUrl) {
        this.name = name;
        this.gender = gender;
        this.hobbies = hobbies;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
