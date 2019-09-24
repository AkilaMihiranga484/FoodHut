package com.example.foodhut;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodhut.Common.Common;
import com.example.foodhut.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtphone);
        btnSignIn = findViewById(R.id.btnSignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Common.isConnectedToInternet(getBaseContext())) {

                    table_user.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(TextUtils.isEmpty(edtPhone.getText().toString())){
                                Toast.makeText(getApplicationContext(),"Please Enter Phone Number",Toast.LENGTH_SHORT).show();
                            }
                            else if(TextUtils.isEmpty(edtPassword.getText().toString())){
                                Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();
                            }
                            //Check if user not exist in Database
                            else if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                                mDialog.setMessage("Please wait...");
                                mDialog.show();
                                //Get user information
                                mDialog.dismiss();
                                User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                                user.setPhone(edtPhone.getText().toString());//set phone
                                if (user.getPhone().equals("0778990410") && user.getName().equals("Admin") && user.getPassword()
                                        .equals(edtPassword.getText().toString())) {

                                    Toast.makeText(SignIn.this, "Sign in success!!!", Toast.LENGTH_SHORT).show();
                                    Intent homeIntent = new Intent(SignIn.this, AdminHome.class);
                                    Common.currentUser = user;
                                    startActivity(homeIntent);
                                    finish();
                                } else if (user.getPassword().equals(edtPassword.getText().toString())) {

                                    Toast.makeText(SignIn.this, "Sign in success!!!", Toast.LENGTH_SHORT).show();
                                    Intent homeIntent = new Intent(SignIn.this, Home.class);
                                    Common.currentUser = user;
                                    startActivity(homeIntent);
                                    finish();
                                } else {

                                    Toast.makeText(SignIn.this, "Wrong password!!!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignIn.this, "User not exist!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(SignIn.this, "Please Check Your Internet Connection !", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        });
    }
}
