package com.restaurant.server.service.food;

import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import com.restaurant.server.model.service.food.FoodServiceModel;
import com.restaurant.server.service.seed.Seed;

import java.util.List;
import java.util.Optional;

public interface FoodService extends Seed {

    List<FoodServiceModel> getAll();

    List<FoodServiceModel> findAllByType(FoodTypeEnum foodType);

    FoodServiceModel findByIdAndType(Long id, FoodTypeEnum foodType);

    boolean isExistByIdAndType(Long id, FoodTypeEnum foodType);

    List<FoodServiceModel> getAllByUser(String username);

    boolean deleteById(Long id, String username);

    FoodServiceModel findByIdAndUser(Long id, String username);

    void updateMeal(Long foodId, FoodServiceModel foodServiceModel);

    void createMeal(FoodServiceModel foodServiceModel, String username);
}
