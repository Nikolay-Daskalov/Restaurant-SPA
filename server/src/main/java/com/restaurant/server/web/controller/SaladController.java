package com.restaurant.server.web.controller;

import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import com.restaurant.server.model.view.FoodCardViewModel;
import com.restaurant.server.service.food.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "*")
public class SaladController {

    private final FoodService foodService;
    private final ModelMapper modelMapper;

    public SaladController(FoodService foodService, ModelMapper modelMapper) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/salads")
    public ResponseEntity<List<FoodCardViewModel>> getAllFood() {
        List<FoodCardViewModel> salads = this.foodService.findAllByType(FoodTypeEnum.SALAD)
                .stream().map(food -> this.modelMapper.map(food, FoodCardViewModel.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(salads);
    }
}
