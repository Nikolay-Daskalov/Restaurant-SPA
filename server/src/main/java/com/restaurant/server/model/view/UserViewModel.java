package com.restaurant.server.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserViewModel {

    private String username;

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
