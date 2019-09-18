package com.example.foodhut.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhut.Interface.ItemClickListener;
import com.example.foodhut.R;

public class HandleUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtName,txtPhone;

    private ItemClickListener itemClickListener;

    public HandleUserViewHolder(@NonNull View itemView) {
        super(itemView);

        txtName = (TextView)itemView.findViewById(R.id.user_name);
        txtPhone = (TextView)itemView.findViewById(R.id.user_phone);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public HandleUserViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

}
