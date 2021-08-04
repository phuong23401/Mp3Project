package com.karaoke.mp3project.security.userprincipal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.karaoke.mp3project.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String username;

    private final String email;

    private final String avatarUrl;
    private final String gender;
    private final String name;
    private final String hobbies;

    @JsonIgnore
    private final String password;

    private final User user;

    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDetails(Long id, String username, String email, String avatarUrl, String gender, String name, String hobbies, String password, User user, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.gender = gender;
        this.name = name;
        this.hobbies = hobbies;
        this.password = password;
        this.user = user;
        this.authorities = authorities;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getHobbies() {
        return hobbies;
    }

    public User getUser() {
        return user;
    }

    public static UserDetails build(User user) {
        List<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetails(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getAvatarUrl(),
                user.getGender(),
                user.getName(),
                user.getHobbies(),
                user.getPassword(),
                user,
                authorities
        );
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                ", authorities=" + authorities +
                '}';
    }
    //    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }
}
