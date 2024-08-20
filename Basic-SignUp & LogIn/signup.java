package com.example.dineease_login;

import android.content.Intent;
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

public class signup extends AppCompatActivity {

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        TextView signup = (TextView) findViewById(R.id.textView2);
        EditText fname = (EditText) findViewById(R.id.editTextText2);
        EditText lname = (EditText) findViewById(R.id.editTextText3);
        EditText uname = (EditText) findViewById(R.id.editTextText4);
        EditText pass = (EditText) findViewById(R.id.editTextText5);
        EditText cpass = (EditText) findViewById(R.id.editTextTextPassword2);
        Button button = (Button) findViewById(R.id.button4);
        Intent intent = new Intent(signup.this, homepage.class);
        db = new DBHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name=fname.getText().toString();
                String Last_name = lname.getText().toString();
                String Username = uname.getText().toString();
                String password = pass.getText().toString();
                String copass = cpass.getText().toString();

                Boolean checkdata = db.insertuserdata(first_name,Last_name,Username,password,copass);
                if(checkdata==true){
                    Toast.makeText(signup.this,"New Entry inserted",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(signup.this,"New Entry not inserted",Toast.LENGTH_SHORT).show();
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