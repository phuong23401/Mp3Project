package com.karaoke.mp3project.model;


public class Password {
    private String id;
    private String password;
    private String newPassword;



    public Password(String id, String password, String newPassword) {
        this.id = id;
        this.password = password;
        this.newPassword = newPassword;
    }

    public Password(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
