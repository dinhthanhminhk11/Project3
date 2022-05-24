package com.example.foodandwallpaper.database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodandwallpaper.model.Ingredients;
import com.example.foodandwallpaper.model.Key;
import com.example.foodandwallpaper.model.Method;
import com.example.foodandwallpaper.model.Recipes;
import com.example.foodandwallpaper.model.TypeRecipes;
import com.example.foodandwallpaper.model.Wallpaper;


@androidx.room.Database(entities = {TypeRecipes.class, Recipes.class, Method.class, Ingredients.class, Wallpaper.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database database;

    public static synchronized Database getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, Database.class, Key.key_database_name)
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }

    public abstract TypeRecipesDao typeRecipesDao();

    public abstract RecipesDao recipesDao();

    public abstract MethodDao methodDao();

    public abstract IngredientsDao ingredientsDao();

    public abstract WallpaperDao wallpaperDao();
}
