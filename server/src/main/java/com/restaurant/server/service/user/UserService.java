package com.restaurant.server.service.user;

import com.restaurant.server.model.service.user.UserServiceModel;
import com.restaurant.server.service.seed.Seed;

public interface UserService extends Seed {

    UserServiceModel findUserByName(String username);

    void register(UserServiceModel user);

    boolean isExistUser(String username);
}
