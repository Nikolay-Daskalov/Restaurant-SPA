package com.restaurant.server.model.entity.food;

import com.restaurant.server.model.entity.base.BaseEntity;
import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import com.restaurant.server.model.entity.rating.RatingEntity;
import com.restaurant.server.model.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "foods")
@NoArgsConstructor
@Getter
public class FoodEntity extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private String imgUrl;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodTypeEnum foodType;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredients;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String recipe;
    @ManyToOne(optional = false)
    private UserEntity author;
    @OneToMany(mappedBy = "food")
    private Set<RatingEntity> ratings;

    public FoodEntity setName(String name) {
        this.name = name;
        return this;
    }

    public FoodEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public FoodEntity setFoodType(FoodTypeEnum foodType) {
        this.foodType = foodType;
        return this;
    }

    public FoodEntity setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public FoodEntity setRecipe(String recipe) {
        this.recipe = recipe;
        return this;
    }

    public FoodEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
