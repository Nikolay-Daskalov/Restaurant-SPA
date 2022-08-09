package com.restaurant.server.repository.rating;

import com.restaurant.server.model.entity.food.FoodEntity;
import com.restaurant.server.model.entity.rating.RatingEntity;
import com.restaurant.server.model.entity.user.UserEntity;
import com.restaurant.server.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends BaseRepository<RatingEntity> {

    boolean existsByFoodAndUser(FoodEntity food, UserEntity user);
}
