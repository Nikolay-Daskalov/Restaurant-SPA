package com.restaurant.server.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RatingViewModel {

    private Integer stars;
    private Long peopleCount;

    public RatingViewModel(Integer stars, Long peopleCount) {
        this.stars = stars;
        this.peopleCount = peopleCount;
    }

    public RatingViewModel setRating(Integer stars) {
        this.stars = stars;
        return this;
    }

    public RatingViewModel setPeopleCount(Long peopleCount) {
        this.peopleCount = peopleCount;
        return this;
    }
}
