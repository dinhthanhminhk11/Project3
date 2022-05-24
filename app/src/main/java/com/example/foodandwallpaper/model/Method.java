package com.example.foodandwallpaper.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "method")
public class Method {

    @PrimaryKey
    public int id;
    private int idRecipes;
    private String contentStep;

    public Method() {
    }

    public Method(String contentStep) {
        this.contentStep = contentStep;
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

    public String getContentStep() {
        return contentStep;
    }

    public void setContentStep(String contentStep) {
        this.contentStep = contentStep;
    }
}
