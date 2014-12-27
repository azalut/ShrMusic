package com.shrmusic.util;

import com.shrmusic.entity.user.Role;

public enum RoleEnum {
    ROLE_USER(new Role("ROLE_USER")), ROLE_ADMIN(new Role("ROLE_ADMIN")), ROLE_TOKENSAVED(new Role("ROLE_TOKENSAVED"));
    private Role role;

    RoleEnum(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
