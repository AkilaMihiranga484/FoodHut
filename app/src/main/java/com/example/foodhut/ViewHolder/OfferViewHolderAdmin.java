package com.example.foodhut.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhut.Common.Common;
import com.example.foodhut.Interface.ItemClickListener;
import com.example.foodhut.R;

public class OfferViewHolderAdmin extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnCreateContextMenuListener {

    public TextView offer_name;
    public ImageView offer_image;

    private ItemClickListener itemClickListener;

    public OfferViewHolderAdmin(@NonNull View itemView) {
        super(itemView);

        offer_name = itemView.findViewById(R.id.add_offer_name);
        offer_image = itemView.findViewById(R.id.add_offer_image);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        //itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select Action");

        contextMenu.add(0,0,getAdapterPosition(), Common.UPDATE);
        contextMenu.add(0,1,getAdapterPosition(),Common.DELETE);
    }
}
