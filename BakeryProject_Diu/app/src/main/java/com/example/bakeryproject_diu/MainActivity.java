package com.example.bakeryproject_diu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // These are the global variables
        EditText username, password;
        Button btnLogin, btnSignUpFb;
        DbAccount DB;

        username  = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUpFb = (Button) findViewById(R.id.btnSignUpFb);

        DB = new DbAccount(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean check = DB.checkUsernameAndPassword(user, pass);
                    if(check == true){
                        Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid account", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnSignUpFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}