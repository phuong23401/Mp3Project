package com.karaoke.mp3project.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Vui lòng nhập tên của bạn!")
    @Pattern(regexp = "^[\\D]+", message = "Hãy đảm bảo bạn nhập đúng tên!")
    private String name;

    @NotNull(message = "Vui lòng nhập email của bạn!")
    @Email(message = "Không đúng định dạng email!")
    private String email;

    @NotNull(message = "Vui lòng nhập tên tài khoản của bạn!")
    @Pattern(regexp = "^[a-z0-9]{4,15}", message = "Nhập chữ cái thường hoặc số từ 4 đến 15 kí tự!")
    private String username;

    @NotNull(message = "Vui lòng nhập mật khẩu của bạn!")
    private String password;

    private String gender;

    private String hobbies;

    private String avatarUrl;

    private Boolean isVerifyEmail = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role = new HashSet<>();

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    public User(Long id, String name, String email, String username, String password, String gender, String hobbies, String avatarUrl, Boolean isVerifyEmail, Set<Role> role, String verificationCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.hobbies = hobbies;
        this.avatarUrl = avatarUrl;
        this.isVerifyEmail = isVerifyEmail;
        this.role = role;
        this.verificationCode = verificationCode;
    }

    public User(Long id, String name, String email, String username, String password, String gender, String hobbies, String avatarUrl, Boolean isVerifyEmail, Set<Role> role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.hobbies = hobbies;
        this.avatarUrl = avatarUrl;
        this.isVerifyEmail = isVerifyEmail;
        this.role = role;
    }



    public User() {
    }

    public User(String name, String email, String username, String password, String gender, String hobbies, String avatarUrl, Set<Role> role) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.hobbies = hobbies;
        this.avatarUrl = avatarUrl;
        this.role = role;
    }

    public User(String name, String email, String username, String password, String gender, String hobbies, String avatarUrl) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.hobbies = hobbies;
        this.avatarUrl = avatarUrl;
    }

    public User(Long id, String name, String email, String username, String password, String gender, String hobbies, String avatarUrl, Set<Role> role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.hobbies = hobbies;
        this.avatarUrl = avatarUrl;
        this.role = role;
    }

    public User(String name, String gender, String hobbies, String avatarUrl) {
        this.name = name;
        this.gender = gender;
        this.hobbies = hobbies;
        this.avatarUrl = avatarUrl;
    }

    public User(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
    public Boolean getVerifyEmail() {
        return isVerifyEmail;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setVerifyEmail(Boolean verifyEmail) {
        isVerifyEmail = verifyEmail;
    }
}
