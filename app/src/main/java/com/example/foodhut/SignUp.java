package com.example.foodhut;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

public class SignUp extends AppCompatActivity {

    MaterialEditText Phone,Name,Password;
    Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name = findViewById(R.id.cusName);
        Password = findViewById(R.id.cusPassword);
        Phone = findViewById(R.id.cusPhone);

        SignUp = findViewById(R.id.btnSignUp);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(Phone.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter Phone Number",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Name.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter Name",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Password.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();
                }
                else if(Common.isConnectedToInternet(getBaseContext())) {

                   final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                   mDialog.setMessage("Please wait...");
                   mDialog.show();

                   table_user.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           //Check if user phone number already exist
                           if (dataSnapshot.child(Phone.getText().toString()).exists()) {
                               mDialog.dismiss();
                               Toast.makeText(SignUp.this, "Phone Number Already Registered!!!", Toast.LENGTH_SHORT).show();

                           } else {
                               mDialog.dismiss();
                               User user = new User(Name.getText().toString(), Password.getText().toString());
                               table_user.child(Phone.getText().toString()).setValue(user);
                               Toast.makeText(SignUp.this, "Sign Up successfully", Toast.LENGTH_SHORT).show();
                               finish();
                           }

                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });
               }
               else
               {
                   Toast.makeText(SignUp.this, "Please Check Your Internet Connection !", Toast.LENGTH_SHORT).show();
                   return;
               }
            }
        });

    }
}
