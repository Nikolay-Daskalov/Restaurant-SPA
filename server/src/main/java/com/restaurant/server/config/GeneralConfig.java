package com.restaurant.server.config;

import com.cloudinary.Cloudinary;
import com.restaurant.server.model.service.food.FoodServiceModel;
import com.restaurant.server.model.service.rating.RatingServiceModel;
import com.restaurant.server.model.view.FoodCardViewModel;
import com.restaurant.server.model.view.FoodDetailViewModel;
import com.restaurant.server.model.view.RatingViewModel;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GeneralConfig extends GlobalMethodSecurityConfiguration {

    private final CloudinaryConfig config;

    public GeneralConfig(CloudinaryConfig config) {
        this.config = config;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
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
    public ModelMapper modelMapper() {
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
        modelMapper.addConverter(new AbstractConverter<FoodServiceModel, FoodDetailViewModel>() {
            @Override
            protected FoodDetailViewModel convert(FoodServiceModel source) {
                FoodDetailViewModel foodDetailViewModel = new FoodDetailViewModel();
                foodDetailViewModel
                        .setId(source.getId())
                        .setAuthor(source.getAuthor().getUsername())
                        .setIngredients(Arrays.stream(source.getIngredients().split("\\s+\\|\\s+")).collect(Collectors.toList()))
                        .setRecipe(source.getRecipe())
                        .setName(source.getName())
                        .setImgUrl(source.getImgUrl())
                        .setRating(getFoodRating(source.getRatings()));

                return foodDetailViewModel;
            }
        });

        return modelMapper;
    }

    private RatingViewModel getFoodRating(Set<RatingServiceModel> ratings) {
        int peopleRated = ratings.size();

        if (peopleRated == 0) {
            return new RatingViewModel(0, 0L);
        }

        int totalRatings = ratings.stream().map(RatingServiceModel::getRating).reduce(0, Integer::sum);

        return new RatingViewModel(totalRatings / peopleRated, (long) peopleRated);
    }
}
