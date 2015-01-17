package com.shrmusic.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 5, max = 40)
    @Column(updatable = false) @NotNull
    private String username;
    @Size(min = 5, max = 40)
    @Column @NotNull @JsonIgnore
    private String password;
    @Column @NotNull
    private boolean enabled;
    @Column @JsonIgnore
    private String accessToken;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, boolean enabled, String accessToken, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accessToken = accessToken;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
