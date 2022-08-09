package com.restaurant.server.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtViewModel {

    private String username;
    private String accessToken;
}
