package com.shrmusic.entity.user;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Role implements Serializable {
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
