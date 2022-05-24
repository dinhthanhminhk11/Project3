package com.example.foodandwallpaper.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.foodandwallpaper.model.Recipes;

import java.util.List;

@Dao
public interface RecipesDao {

    @Query("SELECT * FROM recipes WHERE recipes.idTypeRecipes=:id")
    List<Recipes> getListRecipesById(int id);

    @Query("SELECT * from recipes where recipes.id =:id")
    Recipes getRecipesById(int id);
}
