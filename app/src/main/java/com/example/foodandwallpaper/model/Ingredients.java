package com.example.foodandwallpaper.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredients")
public class Ingredients {
    @PrimaryKey
    public int id;
    private int idRecipes;
    private String nameIngredients;

    public Ingredients() {
    }

    public Ingredients(String nameIngredients) {
        this.nameIngredients = nameIngredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRecipes() {
        return idRecipes;
    }

    public void setIdRecipes(int idRecipes) {
        this.idRecipes = idRecipes;
    }

    public String getNameIngredients() {
        return nameIngredients;
    }

    public void setNameIngredients(String nameIngredients) {
        this.nameIngredients = nameIngredients;
    }
}
