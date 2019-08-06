package com.example.foodhut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText)findViewById(R.id.phone);
        Password = (EditText)findViewById(R.id.password);
        Login = (Button)findViewById(R.id.button123);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPage(Name.getText().toString(),Password.getText().toString());
            }
        });
    }

    public void loginPage(String userName, String userPassword) {
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(Login.this, AdminHome.class);
            startActivity(intent);
        }

        else if((userName.equals("User")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
        }

    }

}
