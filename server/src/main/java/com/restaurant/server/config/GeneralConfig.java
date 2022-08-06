package com.restaurant.server.config;

import com.cloudinary.Cloudinary;
import com.restaurant.server.model.service.food.FoodServiceModel;
import com.restaurant.server.model.service.rating.RatingServiceModel;
import com.restaurant.server.model.view.FoodCardViewModel;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Set;

@Configuration
public class GeneralConfig {

    private final CloudinaryConfig config;

    public GeneralConfig(CloudinaryConfig config) {
        this.config = config;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(Map.of(
                "api_key", this.config.getApiKey(),
                "api_secret", this.config.getApiSecret(),
                "cloud_name", this.config.getCloudName()
        ));
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(new AbstractConverter<FoodServiceModel, FoodCardViewModel>() {
            @Override
            protected FoodCardViewModel convert(FoodServiceModel source) {
                FoodCardViewModel foodCard = new FoodCardViewModel();
                foodCard
                        .setId(source.getId())
                        .setName(source.getName())
                        .setImgUrl(source.getImgUrl())
                        .setRating(getFoodRating(source.getRatings()));

                return foodCard;
            }
        });

        return modelMapper;
    }

    private Integer getFoodRating(Set<RatingServiceModel> ratings){
        int size = ratings.size();

        if (size == 0){
            return 0;
        }

        int totalRatings = ratings.stream().map(RatingServiceModel::getRating).reduce(0, Integer::sum);

        return totalRatings / size;
    }
}
