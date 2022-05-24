package com.example.foodandwallpaper.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.model.Ingredients;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {
    private List<Ingredients> data;
    private Context context;

    public IngredientsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Ingredients> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ingredients item = data.get(position);
        if (item != null) {
            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#ECF0F3"));
            } else {
                holder.itemView.setBackgroundColor(Color.WHITE);
            }
            holder.ingredient.setText(item.getNameIngredients());
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconplus;
        private TextView ingredient;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconplus = (ImageView) itemView.findViewById(R.id.iconplus);
            ingredient = (TextView) itemView.findViewById(R.id.ingredient);

        }
    }
}
