package com.example.foodandwallpaper.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.databinding.FragmentIngredientsBinding;
import com.example.foodandwallpaper.model.Ingredients;
import com.example.foodandwallpaper.model.Recipes;
import com.example.foodandwallpaper.ui.activity.RecipesActivity;
import com.example.foodandwallpaper.ui.adapter.IngredientsAdapter;
import com.example.foodandwallpaper.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class IngredientsFragment extends Fragment {
    private FragmentIngredientsBinding binding;
    private IngredientsAdapter adapter;
    private ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentIngredientsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        adapter = new IngredientsAdapter(getActivity());
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        adapter.setData(RecipesActivity.dataIngredients);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
    }
}