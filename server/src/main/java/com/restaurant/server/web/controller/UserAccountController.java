package com.restaurant.server.web.controller;

import com.restaurant.server.model.binding.FoodBindingModel;
import com.restaurant.server.model.service.food.FoodServiceModel;
import com.restaurant.server.model.view.FoodCardViewModel;
import com.restaurant.server.service.food.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/account")
@PreAuthorize("isFullyAuthenticated()")
@CrossOrigin("*")
public class UserAccountController {

    private final FoodService foodService;
    private final ModelMapper modelMapper;

    public UserAccountController(FoodService foodService, ModelMapper modelMapper) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/meals")
    public ResponseEntity<?> getMealsCreated(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<FoodServiceModel> allByUser = foodService.getAllByUser(principal);

        return ResponseEntity.ok(allByUser.stream().map(foodServiceModel -> this.modelMapper.map(foodServiceModel, FoodCardViewModel.class)).collect(Collectors.toList()));
    }

    @DeleteMapping("/meals/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable String id){

        boolean isDeleted = this.foodService.deleteById(Long.parseLong(id), (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (!isDeleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/meals/{id}")
    public ResponseEntity<FoodBindingModel> getMealById(@PathVariable String id){

        FoodServiceModel byIdAndUser = this.foodService.findByIdAndUser(Long.parseLong(id), (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (byIdAndUser == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(this.modelMapper.map(byIdAndUser, FoodBindingModel.class));
    }

    @PutMapping("/meals/{id}")
    public ResponseEntity<?> putMeal(@PathVariable String id,
                                     @Valid @RequestBody(required = false) FoodBindingModel food,
                                     BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        FoodServiceModel byIdAndUser = this.foodService.findByIdAndUser(Long.parseLong(id), (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (byIdAndUser == null){
            return ResponseEntity.badRequest().build();
        }

        this.foodService.updateMeal(Long.parseLong(id), this.modelMapper.map(food, FoodServiceModel.class));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/meal")
    public ResponseEntity<?> createMeal(@Valid @RequestBody FoodBindingModel food,
                                        BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        this.foodService.createMeal(this.modelMapper.map(food, FoodServiceModel.class), (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return ResponseEntity.ok().build();
    }
}
