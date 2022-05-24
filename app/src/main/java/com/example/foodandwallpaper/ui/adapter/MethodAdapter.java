package com.example.foodandwallpaper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.model.Method;

import java.util.List;

public class MethodAdapter extends RecyclerView.Adapter<MethodAdapter.MyViewHolder> {
    private List<Method> data;
    private Context context;

    public MethodAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Method> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_method, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Method item = data.get(position);
        if (item != null) {
            holder.content.setText(item.getContentStep());
            holder.step.setText("Step " + (position + 1));
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView step;
        private TextView content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            step = (TextView) itemView.findViewById(R.id.step);
            content = (TextView) itemView.findViewById(R.id.content);

        }
    }
}
