package com.example.foodandwallpaper.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "wallpaper")
public class Wallpaper implements Serializable {

    @PrimaryKey
    public int id;
    private String image;

    public Wallpaper() {
    }

    public Wallpaper(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
