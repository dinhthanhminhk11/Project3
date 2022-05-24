package com.example.foodandwallpaper.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.foodandwallpaper.ui.fragment.ContentFragment;
import com.example.foodandwallpaper.ui.fragment.IngredientsFragment;
import com.example.foodandwallpaper.ui.fragment.MethodFragment;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 1:
                return new IngredientsFragment();

            case 2:
                return new MethodFragment();

            default:
                return new ContentFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Content";
                break;
            case 1:
                title = "Ingredients";
                break;
            case 2:
                title = "Method";
                break;
        }
        return title;
    }
}
