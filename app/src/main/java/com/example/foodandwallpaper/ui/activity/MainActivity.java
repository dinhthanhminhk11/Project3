package com.example.foodandwallpaper.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.databinding.ActivityMainBinding;
import com.example.foodandwallpaper.model.Key;
import com.example.foodandwallpaper.ui.fragment.FoodFragment;
import com.example.foodandwallpaper.ui.fragment.SettingFragment;
import com.example.foodandwallpaper.ui.fragment.WallpaperFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binDing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binDing = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binDing.getRoot());

        processAsset();
        loadFragment(new FoodFragment());

        binDing.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.food:
                    loadFragment(new FoodFragment());
                    return true;
                case R.id.wallpaper:
                    loadFragment(new WallpaperFragment());
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void processAsset() {
        File file = getDatabasePath(Key.key_database_name);
        Log.e("Tab", "open success " + file);
        if (!file.exists()) {
            copy();
        } else {
            file.delete();
            copy();
        }
    }

    private void copy() {
        try {
            InputStream inputStream = getAssets().open(Key.key_database_name);
            String output = getApplicationInfo().dataDir + Key.key_database_path + Key.key_database_name;
            File file = new File(getApplicationInfo().dataDir + Key.key_database_path);
            if (!file.exists()) {
                file.mkdir();
            }

            OutputStream outputStream = new FileOutputStream(output);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}