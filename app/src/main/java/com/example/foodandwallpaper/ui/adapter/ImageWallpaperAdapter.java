package com.example.foodandwallpaper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.model.Wallpaper;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ImageWallpaperAdapter extends RecyclerView.Adapter<ImageWallpaperAdapter.ViewHolder> {
    private List<Wallpaper> data;
    private Context context;
    private Callback callback;

    public ImageWallpaperAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void setData(List<Wallpaper> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imagewallpaper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Wallpaper item = data.get(position);
        if (item != null) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage);
            Glide.with(context).load(item.getImage()).apply(options).into(holder.image);
            holder.itemView.setOnClickListener(v -> {
                callback.onClick(item, position);
            });
            getDayAndTime(1, holder.house);
            getDayAndTime(2, holder.day);
        }
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView house;
        private TextView day;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            image = (ImageView) itemView.findViewById(R.id.image);
            house = (TextView) itemView.findViewById(R.id.house);
            day = (TextView) itemView.findViewById(R.id.day);

        }
    }

    public interface Callback {
        void onClick(Wallpaper wallpaper, int position);
    }

    private void getDayAndTime(int isCheck, TextView view) {
        if (isCheck == 1) {
            SimpleDateFormat house = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String currentTime = house.format(new Date());
            view.setText(currentTime);

        } else {
            SimpleDateFormat day = new SimpleDateFormat("EEEE, dd/MM", Locale.getDefault());
            String currentDay = day.format(new Date());
            view.setText(currentDay);
        }
    }
}
