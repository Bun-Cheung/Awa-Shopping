package com.awareness.shopping.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.awareness.shopping.R;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {

    private List<Integer> couponList;
    private onItemClickListener onItemClickListener;

    public CouponAdapter(List<Integer> couponList) {
        this.couponList = couponList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView couponImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            couponImage = itemView.findViewById(R.id.coupon_image);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int imageId = couponList.get(position);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                onItemClickListener.onClick(position);
            });
        }
        holder.couponImage.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public interface onItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(CouponAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
