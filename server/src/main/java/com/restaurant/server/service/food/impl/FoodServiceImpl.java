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
        initFood("Snejanka salad", FoodTypeEnum.SALAD,
                "yogurt - 2200 ml | pickles - 650 g. | garlic - 4 cloves | salt - 2 tea s. | oil - 80 ml | walnuts - 80 g. | dill - fresh 1/2 link",
                "Cucumbers are cut into small cubes. Add the pre-squeezed yogurt (or 770 g of condensed milk), the finely chopped dill, the oil, the garlic crushed with salt, the ground walnuts and mix well. Garnish with sprigs of parsley.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660220374/Restaurant/Menu/Meal/Salad/snejanka-salata.jpg");
        initFood("Beef burger", FoodTypeEnum.BURGER,
                "minced meat - 400 g. beef | bread buns - 3 pieces | egg yolks - 2 pieces | mustard - 2 t.s. | onion - 1 head red | sauce - 3 t.s. BBQ | pickles - 3 pieces | cheese - 3 slices melted | tomatoes - 1 piece | iceberg salad - 3 leaves | olive oil - 4 t.s. | salt | black pepper",
                "Mix the minced meat with 2 tbsp. mustard, egg yolks and finely chopped red onion. Season with salt and pepper. Mix the minced meat well and form three equally sized meatballs. Press lightly to flatten like burgers. Place them on a plate greased with olive oil and put in the fridge for about 30 minutes. Heat the olive oil in a non-stick pan on the stove and fry the burgers for 5 minutes on each side. Cut the buns and grill the lower part of the burgers cut side down for 2 minutes. Place them in a pan, place one slice of the melted cheese each. Grill them for about a minute, just enough to melt the cheese. Arrange the burger by putting a tablespoon of barbecue sauce on top of the melted cheese, then place a slice of tomato and julienned pickles, the beef burger, a sheet of iceberg lettuce and the top bun. Serve with fries if desired",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660221139/Restaurant/Menu/Meal/Burger/beef-burger.jpg");
        initFood("Pork burger", FoodTypeEnum.BURGER,
                "bread buns - 4 pieces for burger | meatballs - 4 pieces pork | green salad - 8 leaves | onion - 1 head | tomato - 1 piece | smoked cheese - 4 pieces | mayo | mustard | BBQ sauce",
                "Meatballs and onions, cut into rings, are grilled. Burger buns are cut in half and also grilled and spread with mayonnaise on both halves. The salad leaves are washed well, the tomato is cut into rings. After everything is ready, we start arranging the burgers. We put a leaf of green salad, mustard on top, then a circle of tomato and barbecue sauce, on top the meatball and onion. On top of them again mustard, a piece of cheese and on top a leaf of green salad and barbecue sauce and we finish by placing the lid of the bread on top. This is how the rest of the burgers are prepared. They are consumed warm and, if desired, with fries on the side.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660221869/Restaurant/Menu/Meal/Burger/pork-burger.jpg");
        initFood("CheeseBurger burger", FoodTypeEnum.BURGER,
                "chicken meat - 1 breast | soy sauce - 1 t.s | black pepper - on taste | salt - 1 tea s. | garlic - 1 t.s. broken | cheddar cheese - 1 pieces each burger slice | tomatoes - 1 piece | onion - 1 small head | bread buns - 2-4 pieces | butter | lettuce",
                "Grind the chicken and season it with the remaining spices and ingredients. Put the mixture in the refrigerator for 30-40 minutes. Then divide it into 2-3-4 parts (depending on how many cheeseburgers you want to make and how thick the meatball is). Roll the minced meat into a ball with greased hands and place it between two sheets of cooking paper. Roll out lightly on a flat surface using a rolling pin or a bottle. Bake the meatballs on a grill or plate. Then put a slab of Cheddar cheese on top of each meatball and put it in the oven or grill until it melts. Cut the loaves in half and spread a thin film of butter on the halves. Place them briefly on the grill or plate. Place a piece of lettuce, a slice of tomato, a chicken meatball, onion rings, tomato again on the bottom bread and press with the top bread. Consume as desired with ketchup, mayonnaise, mustard and with pleasure.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660222361/Restaurant/Menu/Meal/Burger/cheese-burger.jpg");
        initFood("Peperoni pizza", FoodTypeEnum.PIZZA,
                "cheese - 320 g. | dough - 1 for pizza, ready | mozzarella - 100 g. | salami - 150 g. \"pepperoni\" | olives - 50 g. | tomato sauce - 50 ml.",
                "From the finished dough, we roll out a pizza crust, the size according to our taste. Cut the salami, olives and mozzarella. Put the tomato sauce, olives and mozzarella on top of the dough. Add the grated cheese and arrange the sliced salami on top. Bake in a preheated oven at 180 degrees for about 15-18 minutes.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660229849/Restaurant/Menu/Meal/Pizza/peperoni-pizza.jpg");

        initFood("Margarita pizza", FoodTypeEnum.PIZZA,
                "flour - 225 g. | salt - 1 t.s. | yeast - 1 t.s. dry | water - 6 tb.s. lukewarm | tomatoes - 6 pieces sliced | mozzarella - 175 g. | black pepper | salt | basil | olive oil",
                "",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660230230/Restaurant/Menu/Meal/Pizza/margarita-pizza.jpg");
        initFood("Carbonara pizza", FoodTypeEnum.PIZZA,
                "flour - 2 t.s. | sugar - 1-2 t.s | oil - 2 tb.s. | eggs - 1 piece | yeast - 12 g. | salt - 1/2 t.s. | water - 1/2 t.s. or fresh milk | bacon - 200 g. | mushrooms - 100 g. | cream - 3-4 tb.s. | cheese - on eye | oregano - on eye",
                "Dissolve the yeast in the warm fresh milk with powdered sugar. Sift the flour and in a well in the middle add all the rest and products without the oil + the activated yeast. Knead a smooth dough, finally mixing in the oil and let it rise covered in aluminum foil in a bowl. The dough is then stretched with hands and placed in a large pan or board sprinkled with breadcrumbs. It is lightly toasted and taken out. Spread cream on top, distribute onion rings as desired, slices of bacon, mushrooms. Sprinkle a pinch or two of salt and oregano. It is baked again and towards the end it is baked for a few minutes with cheese.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660230624/Restaurant/Menu/Meal/Pizza/carbonara-pizza.jpg");
        initFood("Italian pizza", FoodTypeEnum.PIZZA,
                "flour - 500 g. | yeast - 15 g. fresh | water - a little warm | olive oil - 50 ml. | salt | tomatoes - tomato sauce + fresh slices | mozzarella - slices | anchovies - a few | parmesan - freshly grated | basil - dry",
                "Dissolve the yeast in the warm water. Sift the flour onto the kitchen counter and make a well in the middle. Pour the yeast into it with a little warm water, olive oil and salt. Gradually knead an elastic dough. Form it into a ball, wrap it in a cloth towel and leave it in a warm place to rise for about an hour. Knead again for 3 minutes on the counter, shape it into a ball again and leave it to rise again for an hour. Divide the dough into balls about the size of an apple. From the balls, shape the pizza crusts with your hands - pull until the desired size is obtained. You should get oval plates 1/2 cm thick, thinner in the middle and thicker at the ends. Put them in a suitable tray, spread them with tomato sauce. Slices of mozzarella and fresh tomatoes are arranged on top, a few anchovies each, drizzled with olive oil, sprinkled with grated Parmesan cheese and the Italian pizza is baked until ready in a heated oven or oven. If desired, sprinkle with a little basil.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660231447/Restaurant/Menu/Meal/Pizza/italian-pizza.jpg");
        initFood("Carbonara spaghetti", FoodTypeEnum.PASTA,
                "bacon - 125 g. smoked | spaghetti - 350 g. | eggs - 3 pieces | olive oil - 1 tb.s. | sage - 4 leaves | garlic - 1 clove | oil - 50 g. | parmesan - 5 tb.s. | pepper | salt",
                "Heat the olive oil and briefly fry the garlic clove and the sage leaves. Add the finely chopped bacon and saute for about 10 minutes until the bacon is golden brown. Remove the garlic and sage. Meanwhile, place the spaghetti in boiling salted water and cook as directed, being careful not to overcook. Drain them, reserving 1 tsp. from the broth. Add the spaghetti to the bacon, stir and cook for 1-2 minutes. Pour over the pasta with the pre-beaten eggs mixed with the grated parmesan, mix and remove from the heat. Divide into warmed plates and serve immediately.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660238571/Restaurant/Menu/Meal/Pasta/spaghetti-carbonara.jpg");
        initFood("Bolognese spaghetti", FoodTypeEnum.PASTA,
                "spaghetti - 1 package | minced meat - 250 g. | onion - 1 head | tomatoes - 2 pieces | red pepper | black pepper | oregano | basil | salt",
                "Spaghetti is cooked in salted water, when ready, drain. In a saucepan, fry the onion until golden, then add the minced meat and crumble it. Add the spices and mix. Then the tomatoes are peeled and grated at the mincer. Stir and cook until thickened. Pour the sauce over the spaghetti and mix.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660239024/Restaurant/Menu/Meal/Pasta/spaghetti-boloneze.jpg");
        initFood("Lasagne pasta", FoodTypeEnum.PASTA,
                "fresh milk - 750 ml | oil - 50 g. | bay leaf - 1 piece | flour - 50 g. | nutmeg - 1 pinch | bolognese sauce - 1 jar ready | lasagne crusts - 5 tb.s. | parmesan - 5 tb.s. | black pepper | salt",
                "",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660239429/Restaurant/Menu/Meal/Pasta/lasagne-pasta.jpg");
        initFood("Banana-candies dessert", FoodTypeEnum.DESSERT,
                "biscotti - 200 g. | banana - 2 pieces | protein - 1 tb.s. | walnuts - 100 g. | cinnamon - 2 t.s. | powdered sugar - 1 tb.s.",
                "This recipe is a complete invention of mine. I had leftover biscuits, two bananas, some walnuts and I decided to make them into raw candies. Break up the biscuits and put them in the blender. Grind them into flour. Proceed in the same way with the walnuts. Bananas are mashed. In a bowl, mix the biscuits, one teaspoon of cinnamon, the bananas, the protein and a little water. Everything is mixed with hands so that a dough is obtained. It is broken off a little at a time and a ball is formed, which is rolled in a mixture of cinnamon and powdered sugar. The finished candies are left in the refrigerator for a few hours to harden.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660249090/Restaurant/Menu/Meal/Dessert/banana-candies.jpg");
        initFood("Cake-strawberry dessert", FoodTypeEnum.DESSERT,
                "biscuits - 2 packs | yogurt - 2 buckets | muesli - 150 g. | strawberry - 200 g. | bananas - 3 pieces",
                "In a suitable container, layer upon layer ordinary biscuits, yogurt, muesli, with the milk sprinkled thickly, pieces of strawberries and bananas, again muesli, milk, biscuits - until the container is full. The top layer is made of yogurt, sprinkled with muesli and pieces of strawberries for decoration. The cake stays overnight in the refrigerator to soak the biscuits in milk.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660249556/Restaurant/Menu/Meal/Dessert/cake-strawberry-dessert.jpg");
        initFood("Cake-rafaelo dessert", FoodTypeEnum.DESSERT,
                "eggs - 6 pieces | sugar - 180 g. | flour - 160 g. | condensed milk - 400 g. | cow butter - 250 g. | coconut shavings - 120 g. | almonds - 100 g. (raw, peeled and ground) | white chocolate - 100 g. | fresh milk - 100 ml | coconut flakes - for sprinkling",
                "Eggs are separated into whites and yolks. Beat the egg whites with half the sugar until stiff. The yolks are beaten with the remaining sugar until white. Mix the whites with the yolks and mix carefully. The mixture is divided into 3 equal parts. Each piece fits into a 26cm mold. The tops for the cake are baked in a preheated oven at 180 degrees for 12-15 minutes. For the coconut cream, the condensed milk is mixed with the softened cow's butter and whipped until fluffy. Add the coconut shavings, ground almonds and grated chocolate. The cream is stirred and placed in the refrigerator to cool. Place one sponge in a tray. It is syruped with sweetened fresh milk. It is spread with 1/3 of the cream, followed by cream, syrup, cream, cream, syrup and finally the coconut cake is plastered all over with cream. Almond Raffaello Cake is topped with coconut flakes and decorated with Raffaello candies.",
                "https://res.cloudinary.com/dee2hxl5o/image/upload/v1660249854/Restaurant/Menu/Meal/Dessert/cake-rafaelo-dessert.jpg");
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

    @Override
    public void createMeal(FoodServiceModel foodServiceModel, String username) {
        UserEntity user = this.modelMapper.map(this.userService.findUserByName(username), UserEntity.class);
        FoodEntity foodEntity = new FoodEntity();
        foodEntity
                .setName(foodServiceModel.getName())
                .setAuthor(user)
                .setFoodType(foodServiceModel.getFoodType())
                .setIngredients(foodServiceModel.getIngredients())
                .setRecipe(foodServiceModel.getRecipe())
                .setImgUrl(foodServiceModel.getImgUrl());

        this.foodRepository.save(foodEntity);
    }
}
