package com.restaurant.server.service.user;

import com.restaurant.server.model.entity.enums.RoleEnum;
import com.restaurant.server.model.entity.user.RoleEntity;
import com.restaurant.server.model.service.user.RoleServiceModel;
import com.restaurant.server.service.seed.Seed;

public interface RoleService extends Seed {

    RoleServiceModel findRoleByName(RoleEnum name);
}
