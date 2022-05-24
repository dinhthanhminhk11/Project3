package com.example.foodandwallpaper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.model.Wallpaper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.MyViewHolder> {
    private Context context;
    private List<Wallpaper> data;
    private Callback callback;

    public WallpaperAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void setData(List<Wallpaper> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallpaper, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Wallpaper item = data.get(position);
        if (item != null) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage);
            Glide.with(context).load(item.getImage()).apply(options).into(holder.imageview);
            holder.itemView.setOnClickListener(v -> {
                callback.onClick(item, position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = (ImageView) itemView.findViewById(R.id.imageview);

        }
    }

    public interface Callback {
        void onClick(Wallpaper wallpaper, int position);
    }
}
