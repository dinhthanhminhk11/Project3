package com.example.foodandwallpaper.ui.fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.databinding.FragmentWallpaperBinding;
import com.example.foodandwallpaper.model.Key;
import com.example.foodandwallpaper.model.Wallpaper;
import com.example.foodandwallpaper.ui.activity.WallpaperActivity;
import com.example.foodandwallpaper.ui.adapter.WallpaperAdapter;
import com.example.foodandwallpaper.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class WallpaperFragment extends Fragment {
    private FragmentWallpaperBinding binding;
    private WallpaperAdapter adapter;
    private ViewModel viewModel;
    public static List<Wallpaper> listWallpaper = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWallpaperBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        adapter = new WallpaperAdapter(getActivity(), new WallpaperAdapter.Callback() {
            @Override
            public void onClick(Wallpaper wallpaper, int position) {
                Intent intent = new Intent(getActivity(), WallpaperActivity.class);
                intent.putExtra(Key.key_image_wallpaper, String.valueOf(position));
                intent.putExtra(Key.key_wallpaper, wallpaper);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        viewModel.getListWallpaper().observe(getActivity(), items -> {
            listWallpaper = items;
            adapter.setData(items);
        });
        binding.recyclerView.setAdapter(adapter);
    }
}