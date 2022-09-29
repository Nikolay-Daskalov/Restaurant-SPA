package com.restaurant.server.web.controller;

import com.restaurant.server.model.binding.RatingBindingModel;
import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import com.restaurant.server.model.service.food.FoodServiceModel;
import com.restaurant.server.model.view.FoodCardViewModel;
import com.restaurant.server.model.view.FoodDetailViewModel;
import com.restaurant.server.service.food.FoodService;
import com.restaurant.server.service.rating.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/food/{foodType}")
@CrossOrigin(origins = "*")
public class FoodController {

    private final FoodService foodService;
    private final ModelMapper modelMapper;
    private final RatingService ratingService;

    public FoodController(FoodService foodService, ModelMapper modelMapper, RatingService ratingService) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<List<FoodCardViewModel>> getAllFood(@PathVariable String foodType) {
        FoodTypeEnum foodTypeEnum = getFoodTypeFromPath(foodType);

        if (foodTypeEnum == null) {
            return ResponseEntity.badRequest().build();
        }

        List<FoodCardViewModel> salads = this.foodService.findAllByType(foodTypeEnum)
                .stream().map(food -> this.modelMapper.map(food, FoodCardViewModel.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(salads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDetailViewModel> findSaladById(@PathVariable String id, @PathVariable String foodType) {
        FoodTypeEnum foodTypeEnum = getFoodTypeFromPath(foodType);

        if (foodTypeEnum == null) {
            return ResponseEntity.badRequest().build();
        }

        boolean existByIdAndType = this.foodService.isExistByIdAndType(Long.parseLong(id), foodTypeEnum);

        if (!existByIdAndType){
            ResponseEntity.notFound().build();
        }

        FoodDetailViewModel food = this.modelMapper.map(this.foodService.findByIdAndType(Long.parseLong(id), foodTypeEnum), FoodDetailViewModel.class);

        return ResponseEntity.ok(food);
    }

    @PostMapping("{id}/ratings")
    @PreAuthorize("isFullyAuthenticated()")
    public ResponseEntity<?> postRatingForFood(@PathVariable String foodType, @PathVariable String id,
                                               @Valid @RequestBody RatingBindingModel rating, BindingResult bindingResult) {
        FoodTypeEnum foodtypeEnum = getFoodTypeFromPath(foodType);

        if (foodtypeEnum == null) {
            return ResponseEntity.badRequest().build();
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        boolean existByIdAndType = this.foodService.isExistByIdAndType(Long.parseLong(id), foodtypeEnum);

        if (!existByIdAndType) {
            return ResponseEntity.notFound().build();
        }

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean userReviewed = this.ratingService.isUserReviewed(Long.parseLong(id), foodtypeEnum, username);

        if (userReviewed){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        this.ratingService.addRatingToFood(Long.parseLong(id), foodtypeEnum, rating.getStars(), username);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private FoodTypeEnum getFoodTypeFromPath(String path) {
        switch (path) {
            case "salads":
                return FoodTypeEnum.SALAD;
            case "burgers":
                return FoodTypeEnum.BURGER;
            case "pizza":
                return FoodTypeEnum.PIZZA;
            case "pasta":
                return FoodTypeEnum.PASTA;
            case "desserts":
                return FoodTypeEnum.DESSERT;
            default:
                return null;
        }
    }
}
