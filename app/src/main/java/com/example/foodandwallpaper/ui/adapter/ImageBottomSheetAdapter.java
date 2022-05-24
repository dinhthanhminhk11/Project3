package com.example.foodandwallpaper.ui.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodandwallpaper.R;
import com.example.foodandwallpaper.model.Wallpaper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageBottomSheetAdapter extends RecyclerView.Adapter<ImageBottomSheetAdapter.MyViewHolder> {
    private List<Wallpaper> data;
    private Context context;
    private Callback callback;
    private int defaultWidth = 0;
    private int defaultHeight = 0;
    private int lastSelected = -1;
    private RecyclerView.LayoutManager layoutManager;
    private static final int animValue = 50;
    public static int positionIndex;
    public static Wallpaper wallpaper;

    public ImageBottomSheetAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void setData(List<Wallpaper> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageBottomSheetAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallpaper_bottomsheet, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageBottomSheetAdapter.MyViewHolder holder, int position) {
        Wallpaper item = data.get(position);
        wallpaper = item;
        ViewGroup.LayoutParams params = holder.card.getLayoutParams();
        if (position == lastSelected) {
            params.width = defaultWidth + animValue;
            params.height = defaultHeight + animValue;
        } else {
            if (defaultWidth != 0 && defaultHeight != 0) {
                params.width = defaultWidth;
                params.height = defaultHeight;
            }
        }
        if (item != null) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.noimage)
                    .error(R.drawable.noimage);
            Glide.with(context).load(item.getImage()).apply(options).into(holder.imageView);
        }


    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView card;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);

            card.setOnClickListener(v -> {
                int currentPosition = 0;
                 currentPosition = this.getLayoutPosition();
                selection(currentPosition, lastSelected);
                lastSelected = currentPosition;
                callback.onClick(currentPosition, ImageBottomSheetAdapter.wallpaper);
            });

        }

        private void selection(int newPosition, int oldPosition) {
            if (newPosition != oldPosition) {
                ConstraintLayout newView = (ConstraintLayout) layoutManager.findViewByPosition(newPosition);
                ConstraintLayout oldView = (ConstraintLayout) layoutManager.findViewByPosition(oldPosition);
                if (newView != null) {
                    defaultWidth = card.getWidth();
                    defaultHeight = card.getHeight();
                    selectAnimate(newView.getChildAt(0));
                }
                if (oldView != null) {
                    deselectAnimate(oldView.getChildAt(0));
                } else {
                    notifyItemChanged(oldPosition);
                }
            }
        }

        private void selectAnimate(View view) {
            ValueAnimator select = ValueAnimator.ofInt(defaultWidth, defaultWidth + animValue);
            select.setInterpolator(new AccelerateDecelerateInterpolator());
            select.setDuration(300);
            select.start();
            select.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.width = (int) animation.getAnimatedValue();
                    layoutParams.height = (int) (animation.getAnimatedValue()) + defaultHeight - defaultWidth;
                    view.requestLayout();
                }
            });
        }

        private void deselectAnimate(View view) {
            ValueAnimator select = ValueAnimator.ofInt(defaultWidth + animValue, defaultWidth);
            select.setInterpolator(new AccelerateDecelerateInterpolator());
            select.setDuration(300);
            select.start();
            select.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.width = (int) animation.getAnimatedValue();
                    layoutParams.height = (int) (animation.getAnimatedValue()) + defaultHeight - defaultWidth;
                    view.requestLayout();
                }
            });
        }

    }

    public interface Callback {
        void onClick(int position, Wallpaper wallpaper);
    }
}
