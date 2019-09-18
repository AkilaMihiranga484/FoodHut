package com.example.foodhut.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhut.Interface.ItemClickListener;
import com.example.foodhut.R;

public class SpecialOfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView offer_name;
    public ImageView offer_image;

    private ItemClickListener itemClickListener;

    public SpecialOfferViewHolder(@NonNull View itemView) {
        super(itemView);

        offer_name = itemView.findViewById(R.id.offer_name);
        offer_image = itemView.findViewById(R.id.offer_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        //itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
