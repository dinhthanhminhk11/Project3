package com.example.foodandwallpaper.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipes {
    @PrimaryKey
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

    public Recipes(int id, int idTypeRecipes, String nameRecipes, String content, String image, String preandcook, String level, int kcal, String fat, String saturates, String carbs, String sugars, String fibre, String protein, String salt) {
        this.id = id;
        this.idTypeRecipes = idTypeRecipes;
        this.nameRecipes = nameRecipes;
        this.content = content;
        this.image = image;
        this.preandcook = preandcook;
        this.level = level;
        this.kcal = kcal;
        this.fat = fat;
        this.saturates = saturates;
        this.carbs = carbs;
        this.sugars = sugars;
        this.fibre = fibre;
        this.protein = protein;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public int getIdTypeRecipes() {
        return idTypeRecipes;
    }

    public String getNameRecipes() {
        return nameRecipes;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public String getPreandcook() {
        return preandcook;
    }

    public String getLevel() {
        return level;
    }

    public int getKcal() {
        return kcal;
    }

    public String getFat() {
        return fat;
    }

    public String getSaturates() {
        return saturates;
    }

    public String getCarbs() {
        return carbs;
    }

    public String getSugars() {
        return sugars;
    }

    public String getFibre() {
        return fibre;
    }

    public String getProtein() {
        return protein;
    }

    public String getSalt() {
        return salt;
    }
}
