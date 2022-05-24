package com.example.foodandwallpaper.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.databinding.ActivityRecipesBinding;
import com.example.foodandwallpaper.model.Ingredients;
import com.example.foodandwallpaper.model.Key;
import com.example.foodandwallpaper.model.Method;
import com.example.foodandwallpaper.model.Recipes;
import com.example.foodandwallpaper.ui.adapter.ViewPageAdapter;
import com.example.foodandwallpaper.viewmodel.ViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {
    private ActivityRecipesBinding binding;
    private ViewPageAdapter viewPager;
    private ViewModel viewModel;
    public static String idRecipes;
    private Recipes recipes;
    public static List<Method> dataMethod = new ArrayList<>();
    public static List<Ingredients> dataIngredients = new ArrayList<>();
    public static String contentRecipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();

        init();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
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

    private void init() {
        idRecipes = getIntent().getStringExtra(Key.key_id_recipes);
        viewPager = new ViewPageAdapter(getSupportFragmentManager());
        binding.viewpager.setAdapter(viewPager);
        binding.tabLayout.setupWithViewPager(binding.viewpager);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        getDataRecipes();
        showDataRecipes();
    }

    private void showDataRecipes() {
        getSupportActionBar().setTitle(recipes.getNameRecipes());
        Picasso.get().load(recipes.getImage()).into(binding.image);
        binding.prepandcook.setText(recipes.getPreandcook());
        binding.level.setText(recipes.getLevel());
        binding.kcal.setText(String.valueOf(recipes.getKcal()));
        binding.fat.setText(recipes.getFat());
        binding.saturates.setText(recipes.getSaturates());
        binding.carbs.setText(recipes.getCarbs());
        binding.sugars.setText(recipes.getSugars());
        binding.fibre.setText(recipes.getFibre());
        binding.protein.setText(recipes.getProtein());
        binding.salt.setText(recipes.getSalt());
    }

    private void getDataRecipes() {
        recipes = viewModel.getRecipesById(Integer.parseInt(idRecipes));
        contentRecipes = recipes.getContent();
        viewModel.getListIngredientsByIdRecipes(Integer.parseInt(idRecipes)).observe(this , items->{
            dataIngredients = items;
        });
        viewModel.getListMethodByIdRecipes(Integer.parseInt(idRecipes)).observe(this , items->{
            dataMethod = items;
        });
    }
}