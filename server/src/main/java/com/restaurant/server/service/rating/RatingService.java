package com.restaurant.server.service.rating;


import com.restaurant.server.model.entity.enums.FoodTypeEnum;

public interface RatingService {

    void addRatingToFood(Long foodId, FoodTypeEnum foodType, Integer rating, String username);

    boolean isUserReviewed(Long foodId, FoodTypeEnum foodType, String username);
}
