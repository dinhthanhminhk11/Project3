package com.example.foodandwallpaper.model;

public interface BuilderRecipesInterface {
    BuilderRecipesInterface builderId(int id);

    BuilderRecipesInterface builderIdTypeRecipes(int idTypeRecipes);

    BuilderRecipesInterface builderNameRecipes(String nameRecipes);

    BuilderRecipesInterface builderContent(String content);

    BuilderRecipesInterface builderImageRecipes(String imageRecipes);

    BuilderRecipesInterface builderPerAndCook(String perAndCook);

    BuilderRecipesInterface builderLevel(String level);

    BuilderRecipesInterface builderKcal(int kcal);

    BuilderRecipesInterface builderFat(String fat);

    BuilderRecipesInterface builderSaturates(String saturates);

    BuilderRecipesInterface builderCarbs(String carbs);

    BuilderRecipesInterface builderSugar(String sugar);

    BuilderRecipesInterface builderFibre(String fibre);

    BuilderRecipesInterface builderProtein(String protein);

    BuilderRecipesInterface builderSalt(String salt);

    Recipes build();
}
