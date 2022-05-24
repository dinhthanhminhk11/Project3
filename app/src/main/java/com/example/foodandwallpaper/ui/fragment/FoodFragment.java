package com.example.foodandwallpaper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.databinding.FragmentFoodBinding;
import com.example.foodandwallpaper.model.Key;
import com.example.foodandwallpaper.model.TypeRecipes;
import com.example.foodandwallpaper.ui.activity.ListRecipesActivity;
import com.example.foodandwallpaper.ui.adapter.TypeRecipesAdapter;
import com.example.foodandwallpaper.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {
    private FragmentFoodBinding binding;
    private TypeRecipesAdapter adapter;
    private ViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoodBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        adapter = new TypeRecipesAdapter(getActivity(), new TypeRecipesAdapter.CallBack() {
            @Override
            public void onClick(TypeRecipes typeRecipes) {
                Intent intent = new Intent(getActivity() , ListRecipesActivity.class);
                intent.putExtra(Key.key_id_type_recipes , String.valueOf(typeRecipes.getId()));
                startActivity(intent);
            }
        });

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        viewModel.getListTypeRecipes().observe(getActivity() , items ->{
            adapter.setData(items);
        });
        binding.recyclerView.setAdapter(adapter);
    }
}