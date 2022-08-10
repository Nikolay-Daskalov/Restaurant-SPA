package com.restaurant.server.model.binding;

import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@Getter
public class FoodBindingModel {

    @NotEmpty
    @Size(max = 30)
    private String name;
    @NotEmpty
    private String imgUrl;
    @NotNull
    private FoodTypeEnum foodType;
    @NotEmpty
    private String ingredients;
    @NotEmpty
    private String recipe;

    public FoodBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public FoodBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public FoodBindingModel setFoodType(FoodTypeEnum foodType) {
        this.foodType = foodType;
        return this;
    }

    public FoodBindingModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public FoodBindingModel setRecipe(String recipe) {
        this.recipe = recipe;
        return this;
    }
}
