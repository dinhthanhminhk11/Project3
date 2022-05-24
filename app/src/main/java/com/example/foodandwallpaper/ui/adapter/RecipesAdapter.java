package com.example.foodandwallpaper.ui.adapter;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.model.Recipes;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.MyViewHolder> {
    private Context context;
    private List<Recipes> data;
    private Callback callback;

    public RecipesAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void setData(List<Recipes> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Recipes item = data.get(position);
        if (item != null) {
            Picasso.get().load(item.getImage()).into(holder.imageview);
            holder.nameRecipes.setText(item.getNameRecipes());
            holder.levelRecipes.setText(item.getLevel());
            holder.timeRecipes.setText(item.getPreandcook());
            holder.itemView.setOnClickListener(v -> {
                callback.onClick(item);
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageview;
        private TextView nameRecipes;
        private TextView timeRecipes;
        private TextView levelRecipes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = (ImageView) itemView.findViewById(R.id.imageview);
            nameRecipes = (TextView) itemView.findViewById(R.id.nameRecipes);
            timeRecipes = (TextView) itemView.findViewById(R.id.timeRecipes);
            levelRecipes = (TextView) itemView.findViewById(R.id.levelRecipes);

        }
    }

    public interface Callback {
        void onClick(Recipes recipes);
    }
}
