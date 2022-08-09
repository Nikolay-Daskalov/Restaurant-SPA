package com.restaurant.server.model.entity.rating;

import com.restaurant.server.model.entity.base.BaseEntity;
import com.restaurant.server.model.entity.food.FoodEntity;
import com.restaurant.server.model.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@NoArgsConstructor
@Getter
public class RatingEntity extends BaseEntity {

    @Column(nullable = false)
    private Integer rating;
    @ManyToOne(optional = false)
    private UserEntity user;
    @ManyToOne(optional = false)
    private FoodEntity food;

    public RatingEntity setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public RatingEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public RatingEntity setFood(FoodEntity food) {
        this.food = food;
        return this;
    }
}
