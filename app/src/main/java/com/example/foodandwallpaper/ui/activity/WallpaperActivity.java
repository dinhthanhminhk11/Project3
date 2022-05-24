package com.example.foodandwallpaper.ui.activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.databinding.ActivityWallpaperBinding;
import com.example.foodandwallpaper.model.Key;
import com.example.foodandwallpaper.model.Wallpaper;
import com.example.foodandwallpaper.ui.adapter.ImageBottomSheetAdapter;
import com.example.foodandwallpaper.ui.adapter.ImageWallpaperAdapter;
import com.example.foodandwallpaper.ui.fragment.WallpaperFragment;
import com.example.foodandwallpaper.viewmodel.ViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class WallpaperActivity extends AppCompatActivity {
    private ActivityWallpaperBinding binding;
    private ImageWallpaperAdapter adapter;
    private List<Wallpaper> data = new ArrayList<>();
    private String position;
    private ConstraintLayout bottomsheetcontainer;
    private CardView clickWallpaper;
    private CardView clickDownLoad;
    private RecyclerView recyclerView;
    private ImageBottomSheetAdapter adapterImageBottomSheet;
    private WallpaperManager wallpaperManager;
    private Wallpaper wallpaper;
    private Wallpaper wallpaperSetDefault;
    private ViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWallpaperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        initToolbar();

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        data = WallpaperFragment.listWallpaper;
        if (data != null) {
            position = getIntent().getStringExtra(Key.key_image_wallpaper);
            wallpaper = (Wallpaper) getIntent().getSerializableExtra(Key.key_wallpaper);
            data.remove(Integer.parseInt(position));
            data.add(0, wallpaper);
        }


        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerView);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImageWallpaperAdapter(this, new ImageWallpaperAdapter.Callback() {
            @Override
            public void onClick(Wallpaper wallpaper, int position) {
                showDiaLog();
                recyclerView.smoothScrollToPosition(position);
                wallpaperSetDefault = wallpaper;
            }
        });
        adapter.setData(data);
        binding.recyclerView.setAdapter(adapter);
    }

    private void showDiaLog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.bottomsheet, (LinearLayout) bottomSheetDialog.findViewById(R.id.bottomsheetcontainer));

        bottomsheetcontainer = (ConstraintLayout) bottomSheetView.findViewById(R.id.bottomsheetcontainer);
        clickWallpaper = (CardView) bottomSheetView.findViewById(R.id.clickWallpaper);
        clickDownLoad = (CardView) bottomSheetView.findViewById(R.id.clickDownLoad);
        recyclerView = (RecyclerView) bottomSheetView.findViewById(R.id.recyclerView);

        clickWallpaper.setOnClickListener(v -> {
            setDefaultWallpaper(wallpaperSetDefault.getImage());
        });

        clickDownLoad.setOnClickListener(v -> {
            Random random = new Random();
            downloadImageNew(String.valueOf(random.nextInt()), wallpaperSetDefault.getImage());
        });

        setUpImageBottomSheet();

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }

    private void setUpImageBottomSheet() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapterImageBottomSheet = new ImageBottomSheetAdapter(this, new ImageBottomSheetAdapter.Callback() {
            @Override
            public void onClick(int position, Wallpaper wallpaper) {
                binding.recyclerView.smoothScrollToPosition(position);
                wallpaperSetDefault = wallpaper;
            }
        });
        adapterImageBottomSheet.setLayoutManager(layoutManager);
        adapterImageBottomSheet.setData(data);
        recyclerView.setAdapter(adapterImageBottomSheet);
    }

    private void setDefaultWallpaper(String url) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        Glide.with(getApplicationContext()).asBitmap().load(url).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                try {
                    runOnUiThread(() -> {
                        Toast.makeText(WallpaperActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    });
                    wallpaperManager.setBitmap(resource);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }).submit();
    }

    private void downloadImageNew(String filename, String downloadUrlOfImage) {
        try {
            DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(downloadUrlOfImage);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(filename)
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + filename + ".jpg");
            dm.enqueue(request);
            Toast.makeText(this, "Image download Successful.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Image download failed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        binding.toolBar.setBackground(null);
    }

}