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

import com.example.foodandwallpaper.databinding.FragmentMethodBinding;
import com.example.foodandwallpaper.model.Method;
import com.example.foodandwallpaper.ui.activity.RecipesActivity;
import com.example.foodandwallpaper.ui.adapter.MethodAdapter;
import com.example.foodandwallpaper.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class MethodFragment extends Fragment {
    private FragmentMethodBinding binding;
    private MethodAdapter adapter;
    private ViewModel viewModel;
    private List<Method> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMethodBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    private void init() {
        data = new ArrayList<>();

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        adapter = new MethodAdapter(getActivity());
        showData();
    }

    public void showData() {
        adapter.setData(RecipesActivity.dataMethod);
        binding.recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recycleview.setAdapter(adapter);
    }
}