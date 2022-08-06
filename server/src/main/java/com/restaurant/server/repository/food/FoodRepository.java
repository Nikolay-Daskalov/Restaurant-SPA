package com.restaurant.server.repository.food;

import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import com.restaurant.server.model.entity.food.FoodEntity;
import com.restaurant.server.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends BaseRepository<FoodEntity> {

    List<FoodEntity> findAllByFoodType(FoodTypeEnum foodType);
}
