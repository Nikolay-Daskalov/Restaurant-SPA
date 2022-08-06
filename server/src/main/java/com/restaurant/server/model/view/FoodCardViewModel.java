package com.restaurant.server.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FoodCardViewModel {

    private Long id;
    private String name;
    private String imgUrl;
    private Integer rating;

    public FoodCardViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public FoodCardViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public FoodCardViewModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public FoodCardViewModel setRating(Integer rating) {
        this.rating = rating;
        return this;
    }
}
