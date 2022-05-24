package com.example.foodandwallpaper.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.PrimaryKey;

import com.example.foodandwallpaper.model.Ingredients;
import com.example.foodandwallpaper.model.Method;
import com.example.foodandwallpaper.model.Recipes;
import com.example.foodandwallpaper.model.TypeRecipes;
import com.example.foodandwallpaper.model.Wallpaper;
import com.example.foodandwallpaper.repository.Repository;

import java.lang.reflect.Type;
import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<List<TypeRecipes>> getListTypeRecipes() {
        MutableLiveData<List<TypeRecipes>> listMutableLiveData = new MutableLiveData<>();
        if (repository.getListTypeRecipes().size() > 0) {
            listMutableLiveData.postValue(repository.getListTypeRecipes());
        } else {
            listMutableLiveData.postValue(null);
        }
        return listMutableLiveData;
    }

    public MutableLiveData<List<Recipes>> getListRecipesByIdTypeRecipes(int id) {
        MutableLiveData<List<Recipes>> listMutableLiveData = new MutableLiveData<>();
        if (repository.getListRecipesByIdTypeRecipes(id).size() > 0) {
            listMutableLiveData.postValue(repository.getListRecipesByIdTypeRecipes(id));
        } else {
            listMutableLiveData.postValue(null);
        }
        return listMutableLiveData;
    }

    public MutableLiveData<List<Method>> getListMethodByIdRecipes(int id) {
        MutableLiveData<List<Method>> listMutableLiveData = new MutableLiveData<>();
        if (repository.getListMethodByIdRecipes(id).size() > 0) {
            listMutableLiveData.postValue(repository.getListMethodByIdRecipes(id));
        } else {
            listMutableLiveData.postValue(null);
        }
        return listMutableLiveData;
    }

    public MutableLiveData<List<Ingredients>> getListIngredientsByIdRecipes(int id) {
        MutableLiveData<List<Ingredients>> listMutableLiveData = new MutableLiveData<>();
        if (repository.getListIngredientsByIdRecipes(id).size() > 0) {
            listMutableLiveData.postValue(repository.getListIngredientsByIdRecipes(id));
        } else {
            listMutableLiveData.postValue(null);
        }
        return listMutableLiveData;
    }

    public MutableLiveData<List<Wallpaper>> getListWallpaper() {
        MutableLiveData<List<Wallpaper>> listMutableLiveData = new MutableLiveData<>();
        if (repository.getListWallpaper().size() > 0) {
            listMutableLiveData.postValue(repository.getListWallpaper());
        } else {
            listMutableLiveData.postValue(null);
        }
        return listMutableLiveData;
    }

    public TypeRecipes getTypeRecipesById(int id) {
        return repository.getTypeRecipesById(id);
    }

    public Recipes getRecipesById(int id) {
        return repository.getRecipesById(id);
    }

}
