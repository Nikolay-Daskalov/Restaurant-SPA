package com.restaurant.server.model.service.food;

import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import com.restaurant.server.model.service.rating.RatingServiceModel;
import com.restaurant.server.model.service.user.UserServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Getter
public class FoodServiceModel {

    private Long id;
    private String name;
    private String imgUrl;
    private FoodTypeEnum foodType;
    private String ingredients;
    private String recipe;
    private UserServiceModel author;
    private Set<RatingServiceModel> ratings;

    public FoodServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public FoodServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public FoodServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public FoodServiceModel setFoodType(FoodTypeEnum foodType) {
        this.foodType = foodType;
        return this;
    }

    public FoodServiceModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public FoodServiceModel setRecipe(String recipe) {
        this.recipe = recipe;
        return this;
    }

    public FoodServiceModel setAuthor(UserServiceModel author) {
        this.author = author;
        return this;
    }

    public FoodServiceModel setRatings(Set<RatingServiceModel> ratings) {
        this.ratings = ratings;
        return this;
    }
}
