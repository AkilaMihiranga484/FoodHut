package com.example.foodhut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.foodhut.Common.Common;
import com.example.foodhut.Interface.ItemClickListener;
import com.example.foodhut.Model.Request;
import com.example.foodhut.Model.User;
import com.example.foodhut.ViewHolder.HandleOrderViewHolder;
import com.example.foodhut.ViewHolder.HandleUserViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class HandleUsers extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<User, HandleUserViewHolder> adapter;

    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_users);

        //Firebase
        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");

        //Init
        recyclerView = (RecyclerView)findViewById(R.id.listHandleUsers);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        LoadOrders();

    }

    private void LoadOrders() {
        adapter = new FirebaseRecyclerAdapter<User, HandleUserViewHolder>(
                User.class,
                R.layout.handle_user_layout,
                HandleUserViewHolder.class,
                users

        ) {
            @Override
            protected void populateViewHolder(HandleUserViewHolder handleOrderViewHolder, User user, int i) {
                handleOrderViewHolder.txtPhone.setText(adapter.getRef(i).getKey());
                handleOrderViewHolder.txtName.setText(user.getName());

                handleOrderViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

}
