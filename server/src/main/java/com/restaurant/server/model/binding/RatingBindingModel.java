package com.restaurant.server.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter
public class RatingBindingModel {

    @Min(1)
    @Max(5)
    private Integer stars;

    public RatingBindingModel setRating(Integer stars) {
        this.stars = stars;
        return this;
    }
}
