package com.awareness.shopping.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.awareness.shopping.R;
import com.awareness.shopping.entity.Merchant;

import java.util.List;

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder> {
    private List<Merchant> merchantList;
    private onItemClickListener onItemClickListener;

    public MerchantAdapter(List<Merchant> merchantList) {
        this.merchantList = merchantList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView backgroundImage;
        ImageView merchantIcon;
        TextView merchantName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            backgroundImage = itemView.findViewById(R.id.merchant_background);
            merchantIcon = itemView.findViewById(R.id.merchant_icon);
            merchantName = itemView.findViewById(R.id.merchant_name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_merchant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Merchant merchant = merchantList.get(position);
        if(onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                onItemClickListener.onClick(position);
            });
        }
        holder.backgroundImage.setImageResource(merchant.getBackgroundImage());
        holder.merchantIcon.setImageResource(merchant.getIcon());
        holder.merchantName.setText(merchant.getName());
    }

    @Override
    public int getItemCount() {
        return merchantList.size();
    }

    public interface onItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(MerchantAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
