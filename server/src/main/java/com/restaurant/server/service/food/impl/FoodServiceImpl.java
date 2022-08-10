package com.restaurant.server.service.food.impl;

import com.restaurant.server.model.entity.enums.FoodTypeEnum;
import com.restaurant.server.model.entity.food.FoodEntity;
import com.restaurant.server.model.entity.user.UserEntity;
import com.restaurant.server.model.service.food.FoodServiceModel;
import com.restaurant.server.repository.food.FoodRepository;
import com.restaurant.server.service.food.FoodService;
import com.restaurant.server.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public FoodServiceImpl(FoodRepository foodRepository, UserService userService, ModelMapper modelMapper) {
        this.foodRepository = foodRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void seed() {
        if (this.foodRepository.count() > 0) {
            return;
        }

        initFood("Shopska salad", FoodTypeEnum.SALAD,
                "tomatoes - 2 pieces, ripe, red | peppers - 2 pieces, green | cucumbers - 1 piece | onion - 1 head | parsley - a little bit | olives - 4-5 pieces | feta cheese - 150-200 g. | salt - 2 pinches | vinegar - 1 t.s. | olive oil - 1-2 t.s.",
                "Cut the onion into thin half-moons and mash it with a little salt in a large bowl. If desired, you can also use red onion. Cut the tomatoes and cucumbers into pieces and add them to the onions. Roast the peppers, peel them and add them to the salad cut into small strips, then the olives. Mix the salad, salt, pour the vinegar and olive oil dressing. Crumble the cheese over the vegetables, sprinkle with chopped parsley, garnish with an olive in the middle and serve. The salad is enough for 3-4 servings.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/Restaurant/Menu/Meal/Salad/shopska-salata.jpg");
        initFood("Cesar salad", FoodTypeEnum.SALAD,
                "chicken breasts - 2 pieces | iceberg lettuce | cherry tomatoes - 200 g. | cucumbers - 1 piece | mayo - 3 t.s. | yogurt - 3 t.s. | black pepper | red pepper | salt | lemon juice | olive oil | croutons | parmesan",
                "The chicken breasts are seasoned with salt and pepper and grilled /or a ribbed pan/. After they cool down, cut them into pieces. The salad is cleaned, washed and chopped. Add the chopped cucumber and cherry tomatoes. Add the chicken pieces and the croutons. In a separate container, mix the yogurt, mayonnaise and lemon juice. Season with black and red pepper and salt. Season the Caesar salad with the prepared sauce. Grate Parmesan on top.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/Restaurant/Menu/Meal/Salad/cesar-salata.jpg");
        initFood("Frenska salad", FoodTypeEnum.SALAD,
                "potatoes - 3-4 pieces, medium-sized boiled | eggs - 3 pieces boiled | feta cheese - 200 g. | yogurt - 1 bucket (400 g.) | mayo - 1 bucket (200 g.) | olives - 3-4 pieces",
                "Mix the yogurt and mayonnaise. Put some of the sauce in the bottom of a deep bowl. Grate potatoes. Cover them with the sauce. Grate a layer of the cheese. Put a layer of grated eggs. Drizzle with sauce. Alternate like this until you run out of products. Pour sauce on top and grate boiled eggs. Garnish with olives.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/Restaurant/Menu/Meal/Salad/frenska-salata.jpg");
        initFood("Ovcharska salad", FoodTypeEnum.SALAD,
                "tomatoes - 2 pieces | cucumbers - 150 grams | peppers - 1-2 roasted, pilled | onion - 1/4 head red | mushrooms - 100 g. marinated | feta cheese - 120 g. crumbled | ham - 120 g. cut into small pieces | cheese - 100 g. | eggs - 2 hard boiled | oil - 4 t.s. | parsley - several stalks, chopped | vinegar - on taste | salt - on taste | olives - 5 pieces, for decoration",
                "Cut the tomatoes, cucumbers and peppers. Mix them in a bowl and add the onion halves, the mushrooms, the ham and the diced cheese. Mix lightly, pour over the oil, vinegar and salt to taste. Crumble the cheese on top, place quartered eggs and garnish with olives. Serve the finished shepherd's salad with sprigs of parsley.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/Restaurant/Menu/Meal/Salad/ovcharska-salata.jpg");
        initFood("Green salad", FoodTypeEnum.SALAD,
                "green lettuce - 1 piece | fresh garlic - 2-3 cloves | fresh onion - 1 piece | cucumber - 1 piece | radishes - 5-6 pieces, | oil | salt | vinegar",
                "Wash the salad and drain it. Cut it into a large bowl. Peel the garlic and onion. Cut them into rounds. Peel the cucumber. Cut it together with the cleaned radishes. Season the country green salad with salt, vinegar and oil. Serve the traditional green salad immediately.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/Restaurant/Menu/Meal/Salad/green-salad.jpg");
    }

    private void initFood(String name, FoodTypeEnum foodType, String ingredients, String recipe, String imgUrl) {
        UserEntity userByName = this.modelMapper.map(userService.findUserByName("Admin"), UserEntity.class);

        FoodEntity foodEntity = new FoodEntity();
        foodEntity
                .setName(name)
                .setFoodType(foodType)
                .setIngredients(ingredients)
                .setRecipe(recipe)
                .setAuthor(userByName)
                .setImgUrl(imgUrl);

        this.foodRepository.save(foodEntity);
    }

    @Override
    public List<FoodServiceModel> getAll() {
        return this.foodRepository.findAll().stream().map(food -> this.modelMapper.map(food, FoodServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<FoodServiceModel> findAllByType(FoodTypeEnum foodType) {
        return this.foodRepository.findAllByFoodType(foodType)
                .stream()
                .map(food -> this.modelMapper.map(food, FoodServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public FoodServiceModel findByIdAndType(Long id, FoodTypeEnum foodType) {
        return this.foodRepository.findByIdAndFoodType(id, foodType).map(food -> this.modelMapper.map(food, FoodServiceModel.class)).orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean isExistByIdAndType(Long id, FoodTypeEnum foodType) {
        return this.foodRepository.existsByIdAndFoodType(id, foodType);
    }

    @Override
    public List<FoodServiceModel> getAllByUser(String username) {
        UserEntity user = this.modelMapper.map(this.userService.findUserByName(username), UserEntity.class);

        return this.foodRepository.findAllByAuthor(user).stream().map(foodEntity -> this.modelMapper.map(foodEntity, FoodServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id, String username) {
        UserEntity user = this.modelMapper.map(this.userService.findUserByName(username), UserEntity.class);

        boolean isExist = this.foodRepository.existsByIdAndAuthor(id,user);

        if (!isExist){
            return false;
        }

        this.foodRepository.deleteById(id);

        return true;
    }

    @Override
    public FoodServiceModel findByIdAndUser(Long id, String username) {
        UserEntity user = this.modelMapper.map(this.userService.findUserByName(username), UserEntity.class);

        boolean isExist = this.foodRepository.existsByIdAndAuthor(id,user);

        if (!isExist){
            return null;
        }

        return this.modelMapper.map(this.foodRepository.findById(id).get(), FoodServiceModel.class);
    }

    @Override
    @Transactional
    public void updateMeal(Long foodId, FoodServiceModel foodServiceModel) {
        FoodEntity foodEntity = this.foodRepository.findById(foodId).get();
        foodEntity
                .setName(foodServiceModel.getName())
                .setRecipe(foodServiceModel.getRecipe())
                .setIngredients(foodServiceModel.getIngredients())
                .setFoodType(foodServiceModel.getFoodType())
                .setImgUrl(foodServiceModel.getImgUrl());
    }
}
