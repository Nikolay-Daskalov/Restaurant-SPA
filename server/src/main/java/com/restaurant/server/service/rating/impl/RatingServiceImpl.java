package com.restaurant.server.service.rating.impl;

import com.restaurant.server.model.entity.food.FoodEntity;
import com.restaurant.server.model.entity.rating.RatingEntity;
import com.restaurant.server.model.service.food.FoodServiceModel;
import com.restaurant.server.repository.rating.RatingRepository;
import com.restaurant.server.service.food.FoodService;
import com.restaurant.server.service.rating.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ModelMapper modelMapper;
    private final FoodService foodService;

    public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper, FoodService foodService) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
        this.foodService = foodService;
    }


    @Override
    @Transactional
    public void seed() {
        if (this.ratingRepository.count() > 0){
            return;
        }

        init(this.foodService.getAll());
    }

    private void init(List<FoodServiceModel> food){
        List<FoodEntity> foodEntities = food.stream()
                .map(foodServiceModel -> this.modelMapper.map(foodServiceModel, FoodEntity.class))
                .collect(Collectors.toList());

        foodEntities.stream().forEach(foodEntity -> {
            RatingEntity ratingEntity = new RatingEntity();
            ratingEntity
                    .setFood(foodEntity)
                    .setUser(foodEntity.getAuthor())
                    .setRating(ThreadLocalRandom.current().nextInt(0,6));

            this.ratingRepository.save(ratingEntity);
        });
    }
}
















