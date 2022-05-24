package com.example.foodandwallpaper.ui.activity;

import static com.google.android.material.transition.MaterialSharedAxis.X;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.databinding.ActivityListRecipesBinding;
import com.example.foodandwallpaper.model.Key;
import com.example.foodandwallpaper.model.Recipes;
import com.example.foodandwallpaper.model.RecipesBuilder;
import com.example.foodandwallpaper.model.TypeRecipes;
import com.example.foodandwallpaper.ui.adapter.RecipesAdapter;
import com.example.foodandwallpaper.viewmodel.ViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.blurry.Blurry;

public class ListRecipesActivity extends AppCompatActivity {
    private ActivityListRecipesBinding binding;
    private List<Recipes> data = new ArrayList<>();
    private RecipesAdapter adapter;
    private String idTypeRecipes;
    private ViewModel viewModel;
    private TypeRecipes typeRecipes;

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();

        init();

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getListRecipesByIdTypeRecipes(Integer.parseInt(idTypeRecipes)).observe(this, items -> {
            adapter.setData(items);
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        idTypeRecipes = getIntent().getStringExtra(Key.key_id_type_recipes);
        getDataByIdTypeRecipes();
        showDataTypeRecipes();

        adapter = new RecipesAdapter(this, new RecipesAdapter.Callback() {
            @Override
            public void onClick(Recipes recipes) {
                startActivity(new Intent(ListRecipesActivity.this, RecipesActivity.class).putExtra(Key.key_id_recipes, String.valueOf(recipes.getId())));
            }
        });

    }

    private void showDataTypeRecipes() {
        Picasso.get().load(typeRecipes.getImageTypeRecipes()).into(binding.image);
        binding.contentTypeRecipes.setText(typeRecipes.getContentTypeRecipes());
        binding.countRecipes.setText(typeRecipes.getCountRecipes());
        getSupportActionBar().setTitle(typeRecipes.getTypeRecipesName());
    }

    private void getDataByIdTypeRecipes() {
        typeRecipes = viewModel.getTypeRecipesById(Integer.parseInt(idTypeRecipes));
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        binding.collapseToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        binding.collapseToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        binding.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((binding.collapseToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(binding.collapseToolbarLayout))) {
                    binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
                } else {
                    binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });
    }
}