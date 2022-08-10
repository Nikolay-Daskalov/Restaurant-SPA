package com.restaurant.server.repository.food;

import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import com.restaurant.server.model.entity.food.FoodEntity;
import com.restaurant.server.model.entity.user.UserEntity;
import com.restaurant.server.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends BaseRepository<FoodEntity> {

    List<FoodEntity> findAllByFoodType(FoodTypeEnum foodType);

    Optional<FoodEntity> findByIdAndFoodType(Long id, FoodTypeEnum foodType);

    boolean existsByIdAndFoodType(Long id, FoodTypeEnum foodType);

    List<FoodEntity> findAllByAuthor(UserEntity author);

    boolean existsByIdAndAuthor(Long id, UserEntity author);
}
