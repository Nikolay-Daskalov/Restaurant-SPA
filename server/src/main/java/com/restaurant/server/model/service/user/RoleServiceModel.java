package com.restaurant.server.model.service.user;

import com.restaurant.server.model.entity.enums.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Getter
public class RoleServiceModel {

    private Long id;
    private RoleEnum role;
    private Set<UserServiceModel> users;

    public RoleServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public RoleServiceModel setRole(RoleEnum role) {
        this.role = role;
        return this;
    }

    public RoleServiceModel setUsers(Set<UserServiceModel> users) {
        this.users = users;
        return this;
    }
}
