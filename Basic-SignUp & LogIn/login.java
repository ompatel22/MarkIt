package com.example.dineease_login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;



public class login extends AppCompatActivity {
    FileOutputStream fileOutputStream ;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText username = (EditText) findViewById(R.id.editTextText);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);
        Button button = (Button) findViewById(R.id.button3);
        db = new DBHelper(this);
        Intent intent = new Intent(login.this, homepage.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();
                Boolean checkuserpass = db.checkdetails(uname,pass);
                if(checkuserpass==true)
                {
                    Toast.makeText(login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(login.this,"Enter valid username or pass",Toast.LENGTH_SHORT).show();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}