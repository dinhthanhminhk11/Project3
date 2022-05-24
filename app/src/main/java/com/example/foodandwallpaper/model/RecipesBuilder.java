package com.example.foodandwallpaper.model;

public class RecipesBuilder implements BuilderRecipesInterface {
    public int id;
    private int idTypeRecipes;
    private String nameRecipes;
    private String content;
    private String image;
    private String preandcook;
    private String level;
    private int kcal;
    private String fat;
    private String saturates;
    private String carbs;
    private String sugars;
    private String fibre;
    private String protein;
    private String salt;

    @Override
    public BuilderRecipesInterface builderId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderIdTypeRecipes(int idTypeRecipes) {
        this.idTypeRecipes = idTypeRecipes;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderNameRecipes(String nameRecipes) {
        this.nameRecipes = nameRecipes;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderImageRecipes(String imageRecipes) {
        this.image = imageRecipes;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderPerAndCook(String perAndCook) {
        this.preandcook = perAndCook;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderLevel(String level) {
        this.level = level;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderKcal(int kcal) {
        this.kcal = kcal;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderFat(String fat) {
        this.fat = fat;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderSaturates(String saturates) {
        this.saturates = saturates;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderCarbs(String carbs) {
        this.carbs = carbs;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderSugar(String sugar) {
        this.sugars = sugar;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderFibre(String fibre) {
        this.fibre = fibre;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderProtein(String protein) {
        this.protein = protein;
        return this;
    }

    @Override
    public BuilderRecipesInterface builderSalt(String salt) {
        this.salt = salt;
        return this;
    }

    @Override
    public Recipes build() {
        return new Recipes(id, idTypeRecipes, nameRecipes, content, image, preandcook, level, kcal, fat, saturates, carbs, sugars, fibre, protein, salt);
    }
}
