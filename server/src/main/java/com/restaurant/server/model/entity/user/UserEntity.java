package com.restaurant.server.model.entity.user;

import com.restaurant.server.model.entity.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 30)
    private String username;
    @Column(nullable = false, length = 60)
    private String password;
    @ManyToMany
    private Set<RoleEntity> roles;

    public UserEntity() {
        this.roles = new HashSet<>();
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
}
