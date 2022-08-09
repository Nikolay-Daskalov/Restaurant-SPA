package com.restaurant.server.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class FoodDetailViewModel {

    private Long id;
    private String name;
    private String imgUrl;
    private List<String> ingredients;
    private String recipe;
    private String author;
    private RatingViewModel rating;

    public FoodDetailViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public FoodDetailViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public FoodDetailViewModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public FoodDetailViewModel setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public FoodDetailViewModel setRecipe(String recipe) {
        this.recipe = recipe;
        return this;
    }

    public FoodDetailViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public FoodDetailViewModel setRating(RatingViewModel rating) {
        this.rating = rating;
        return this;
    }
}
