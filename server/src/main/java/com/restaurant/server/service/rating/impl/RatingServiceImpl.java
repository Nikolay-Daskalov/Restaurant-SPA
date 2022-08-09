package com.restaurant.server.service.rating.impl;

import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import com.restaurant.server.model.entity.food.FoodEntity;
import com.restaurant.server.model.entity.rating.RatingEntity;
import com.restaurant.server.model.entity.user.UserEntity;
import com.restaurant.server.repository.rating.RatingRepository;
import com.restaurant.server.service.food.FoodService;
import com.restaurant.server.service.rating.RatingService;
import com.restaurant.server.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ModelMapper modelMapper;
    private final FoodService foodService;
    private final UserService userService;

    public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper, FoodService foodService, UserService userService) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
        this.foodService = foodService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void addRatingToFood(Long foodId, FoodTypeEnum foodType, Integer rating, String username) {
        UserEntity user = this.modelMapper.map(this.userService.findUserByName(username), UserEntity.class);
        FoodEntity food = this.modelMapper.map(this.foodService.findByIdAndType(foodId, foodType), FoodEntity.class);

        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity
                .setRating(rating)
                .setFood(food)
                .setUser(user);

        this.ratingRepository.save(ratingEntity);
    }

    @Override
    public boolean isUserReviewed(Long foodId, FoodTypeEnum foodType, String username) {
        UserEntity user = this.modelMapper.map(this.userService.findUserByName(username), UserEntity.class);
        FoodEntity food = this.modelMapper.map(this.foodService.findByIdAndType(foodId, foodType), FoodEntity.class);

        return this.ratingRepository.existsByFoodAndUser(food, user);
    }
}
















