package com.example.foodandwallpaper.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.foodandwallpaper.model.Ingredients;

import java.util.List;

@Dao
public interface IngredientsDao {

    @Query("SELECT * FROM ingredients WHERE ingredients.idRecipes=:id")
    List<Ingredients> getListIngredients(int id);

}
