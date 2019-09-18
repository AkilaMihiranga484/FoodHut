package com.example.foodhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodhut.Interface.ItemClickListener;
import com.example.foodhut.Model.Offer;
import com.example.foodhut.ViewHolder.SpecialOfferViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SpecialOffers extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference offer;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Offer, SpecialOfferViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_offers);

        database = FirebaseDatabase.getInstance();
        offer = database.getReference("Offers");

        //Firebase
        recyclerView = findViewById(R.id.recycler_offer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadMenu();

    }

    private void loadMenu() {
        adapter = new FirebaseRecyclerAdapter<Offer, SpecialOfferViewHolder>(Offer.class,
                R.layout.special_offer_item,
                SpecialOfferViewHolder.class,
                offer
        ) {
            @Override
            protected void populateViewHolder(SpecialOfferViewHolder specialOfferViewHolder, Offer model, int position) {
                specialOfferViewHolder.offer_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(specialOfferViewHolder.offer_image);
                final Offer clickItem = model;
                specialOfferViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

    }
}
