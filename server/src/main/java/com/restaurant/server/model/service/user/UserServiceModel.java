package com.restaurant.server.model.service.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Getter
public class UserServiceModel {

    private Long id;
    private String username;
    private String password;
    private Set<RoleServiceModel> roles;

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserServiceModel setRoles(Set<RoleServiceModel> roles) {
        this.roles = roles;
        return this;
    }
}
