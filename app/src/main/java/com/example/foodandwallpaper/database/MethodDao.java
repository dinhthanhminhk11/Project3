package com.example.foodandwallpaper.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.foodandwallpaper.model.Method;

import java.util.List;

@Dao
public interface MethodDao {

    @Query("SELECT * FROM method WHERE method.idRecipes=:id")
    List<Method> getListMethodById(int id);

}
