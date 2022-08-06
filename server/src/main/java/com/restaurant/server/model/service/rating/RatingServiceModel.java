package com.restaurant.server.model.service.rating;

import com.restaurant.server.model.service.food.FoodServiceModel;
import com.restaurant.server.model.service.user.UserServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RatingServiceModel {

    private Long id;
    private Integer rating;
    private UserServiceModel user;
    private FoodServiceModel food;

    public RatingServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public RatingServiceModel setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public RatingServiceModel setUser(UserServiceModel user) {
        this.user = user;
        return this;
    }

    public RatingServiceModel setFood(FoodServiceModel food) {
        this.food = food;
        return this;
    }
}
