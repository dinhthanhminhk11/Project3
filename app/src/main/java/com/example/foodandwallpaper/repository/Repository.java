package com.example.foodandwallpaper.repository;

import android.app.Application;

import androidx.room.Dao;

import com.example.foodandwallpaper.database.Database;
import com.example.foodandwallpaper.database.IngredientsDao;
import com.example.foodandwallpaper.database.MethodDao;
import com.example.foodandwallpaper.database.RecipesDao;
import com.example.foodandwallpaper.database.TypeRecipesDao;
import com.example.foodandwallpaper.database.WallpaperDao;
import com.example.foodandwallpaper.model.Ingredients;
import com.example.foodandwallpaper.model.Method;
import com.example.foodandwallpaper.model.Recipes;
import com.example.foodandwallpaper.model.TypeRecipes;
import com.example.foodandwallpaper.model.Wallpaper;

import java.lang.reflect.Type;
import java.util.List;

public class Repository {
    private TypeRecipesDao typeRecipesDao;
    private RecipesDao recipesDao;
    private MethodDao methodDao;
    private IngredientsDao ingredientsDao;
    private WallpaperDao wallpaperDao;

    public Repository(Application application) {
        Database database = Database.getInstance(application);
        typeRecipesDao = database.typeRecipesDao();
        recipesDao = database.recipesDao();
        methodDao = database.methodDao();
        ingredientsDao = database.ingredientsDao();
        wallpaperDao = database.wallpaperDao();
    }

    public List<TypeRecipes> getListTypeRecipes() {
        return typeRecipesDao.getListTypeRecipes();
    }

    public List<Recipes> getListRecipesByIdTypeRecipes(int id) {
        return recipesDao.getListRecipesById(id);
    }

    public List<Method> getListMethodByIdRecipes(int id) {
        return methodDao.getListMethodById(id);
    }

    public List<Ingredients> getListIngredientsByIdRecipes(int id) {
        return ingredientsDao.getListIngredients(id);
    }

    public List<Wallpaper> getListWallpaper() {
        return wallpaperDao.getListWallpaper();
    }

    public TypeRecipes getTypeRecipesById(int id) {
        return typeRecipesDao.getTypeRecipesById(id);
    }

    public Recipes getRecipesById(int id) {
        return recipesDao.getRecipesById(id);
    }

}
