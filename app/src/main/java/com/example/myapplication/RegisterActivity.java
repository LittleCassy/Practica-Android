package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DDBBUser;
import com.example.myapplication.db.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText myUsername = findViewById(R.id.editTextTextPersonName);
        EditText myEmail = findViewById(R.id.idEmailReg);
        EditText myPass = findViewById(R.id.idRegPass);

        Button backBtn = findViewById(R.id.idBackReg);
        Button registerBtn = findViewById(R.id.idFuncRegister);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myUsername.getText().toString()== "" || myEmail.getText().toString()=="" || myPass.getText().toString()==""){
                    Toast.makeText(RegisterActivity.this, "Fill everything in order to continue", Toast.LENGTH_SHORT).show();
                }else{
                    DDBBUser myDB = new DDBBUser(RegisterActivity.this);
                    long idTest = myDB.insertUser(myUsername.getText().toString(), myPass.getText().toString(), myEmail.getText().toString());
                    Toast.makeText(RegisterActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(myIntent);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }


}