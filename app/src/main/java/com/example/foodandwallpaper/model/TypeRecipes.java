package com.example.foodandwallpaper.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "typerecipes")
public class TypeRecipes {
    @PrimaryKey
    public int id;
    private String typeRecipesName;
    private String imageTypeRecipes;
    private String countRecipes;
    private String contentTypeRecipes;

    public TypeRecipes() {
    }

    public TypeRecipes(int id, String typeRecipesName, String imageTypeRecipes, String countRecipes, String contentTypeRecipes) {
        this.id = id;
        this.typeRecipesName = typeRecipesName;
        this.imageTypeRecipes = imageTypeRecipes;
        this.countRecipes = countRecipes;
        this.contentTypeRecipes = contentTypeRecipes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeRecipesName() {
        return typeRecipesName;
    }

    public void setTypeRecipesName(String typeRecipesName) {
        this.typeRecipesName = typeRecipesName;
    }

    public String getImageTypeRecipes() {
        return imageTypeRecipes;
    }

    public void setImageTypeRecipes(String imageTypeRecipes) {
        this.imageTypeRecipes = imageTypeRecipes;
    }

    public String getCountRecipes() {
        return countRecipes;
    }

    public void setCountRecipes(String countRecipes) {
        this.countRecipes = countRecipes;
    }

    public String getContentTypeRecipes() {
        return contentTypeRecipes;
    }

    public void setContentTypeRecipes(String contentTypeRecipes) {
        this.contentTypeRecipes = contentTypeRecipes;
    }
}
