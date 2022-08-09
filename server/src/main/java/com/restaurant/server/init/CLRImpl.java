package com.restaurant.server.init;

import com.restaurant.server.service.food.FoodService;
import com.restaurant.server.service.rating.RatingService;
import com.restaurant.server.service.user.RoleService;
import com.restaurant.server.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLRImpl implements CommandLineRunner {

    private final FoodService foodService;
    private final UserService userService;
    private final RoleService roleService;

    public CLRImpl(FoodService foodService, UserService userService, RoleService roleService) {
        this.foodService = foodService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.seed();
        userService.seed();
        foodService.seed();
    }
}
