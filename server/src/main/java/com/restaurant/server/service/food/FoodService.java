package com.restaurant.server.service.food;

import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import com.restaurant.server.model.service.food.FoodServiceModel;
import com.restaurant.server.service.seed.Seed;

import java.util.List;

public interface FoodService extends Seed {

    List<FoodServiceModel> getAll();

    List<FoodServiceModel> findAllByType(FoodTypeEnum foodType);
}
