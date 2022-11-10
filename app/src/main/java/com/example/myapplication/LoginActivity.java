package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.data.ProfileManager;
import com.example.myapplication.db.DDBBUser;
import com.example.myapplication.db.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginBtn = findViewById(R.id.idFuncLogin);
        Button backBtn = findViewById(R.id.idBack);

        EditText myUsername = findViewById(R.id.editTextTextEmailAddress);
        EditText myPass = findViewById(R.id.editTextTextPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myUsername.getText().toString()=="" || myPass.getText().toString()==""){
                    Toast.makeText(LoginActivity.this, "Fill everything in order to continue", Toast.LENGTH_SHORT).show();
                }else{
                    User u = new DDBBUser(LoginActivity.this).getUser(myUsername.getText().toString(), myPass.getText().toString());

                    if(u.isPresent()){
                        Toast.makeText(LoginActivity.this, "Login completed! Welcome!", Toast.LENGTH_SHORT).show();
                        ProfileManager.setEmail(u.email);
                        ProfileManager.setUsername(u.username);
                        ProfileManager.setPoints(u.puntos);

                        Intent myIntent = new Intent(LoginActivity.this, LandingActivity.class);
                        startActivity(myIntent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Uh Oh! Sorry, user not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}