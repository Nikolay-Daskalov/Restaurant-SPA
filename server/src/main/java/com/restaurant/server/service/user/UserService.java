package com.restaurant.server.service.user;

import com.restaurant.server.model.entity.user.UserEntity;
import com.restaurant.server.service.seed.Seed;

public interface UserService extends Seed {

    UserEntity findUserByName(String username);
}
