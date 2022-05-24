package com.example.foodandwallpaper.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.databinding.FragmentContentBinding;
import com.example.foodandwallpaper.model.Recipes;
import com.example.foodandwallpaper.ui.activity.RecipesActivity;
import com.example.foodandwallpaper.viewmodel.ViewModel;

public class ContentFragment extends Fragment {
    private FragmentContentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.content.setText(RecipesActivity.contentRecipes);

    }
}