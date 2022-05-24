package com.example.foodandwallpaper.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.foodandwallpaper.model.TypeRecipes;

import java.util.List;

@Dao
public interface TypeRecipesDao {

    @Query("SELECT * FROM typerecipes")
    List<TypeRecipes> getListTypeRecipes();

    @Query("SELECT * from typerecipes where typerecipes.id =:id")
    TypeRecipes getTypeRecipesById(int id);

}
