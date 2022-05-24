package com.example.foodandwallpaper.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.foodandwallpaper.model.Wallpaper;

import java.util.List;
@Dao
public interface WallpaperDao {

    @Query("SELECT * FROM wallpaper")
    List<Wallpaper> getListWallpaper();

}
