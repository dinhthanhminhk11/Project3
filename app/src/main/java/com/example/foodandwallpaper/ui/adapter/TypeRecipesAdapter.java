package com.example.foodandwallpaper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.model.TypeRecipes;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.List;

public class TypeRecipesAdapter extends RecyclerView.Adapter<TypeRecipesAdapter.MyViewHolder> {
    private List<TypeRecipes> data;
    private Context context;
    private CallBack callBack;

    public TypeRecipesAdapter(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public void setData(List<TypeRecipes> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_typerecipes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TypeRecipes item = data.get(position);
        if (item != null) {
            Picasso.get().load(item.getImageTypeRecipes()).into(holder.imageview);
            holder.title.setText(item.getTypeRecipesName());
            holder.itemView.setOnClickListener(v -> {
                callBack.onClick(item);
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageview;
        private TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = (ImageView) itemView.findViewById(R.id.imageview);
            title = (TextView) itemView.findViewById(R.id.title);

        }
    }

    public interface CallBack {
        void onClick(TypeRecipes typeRecipes);
    }
}
