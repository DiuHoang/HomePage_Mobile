package com.example.bakeryproject_diu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        // These are the global variables
        EditText username, phone, password, confirmPass;
        Button btnSignup;
        DbAccount DB;

        username  = (EditText) findViewById(R.id.usernameSigUp);
        phone = (EditText) findViewById(R.id.phoneNumberSignUp);
        password = (EditText) findViewById(R.id.passwordSignUp);
        confirmPass = (EditText) findViewById(R.id.confirmPassword);

        btnSignup = (Button) findViewById(R.id.btnSignUp);

        DB = new DbAccount(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String phoneNum  = phone.getText().toString();
                String pass = password.getText().toString();
                String confirm = confirmPass.getText().toString();

                if(user.equals("") || phoneNum.equals("")|| pass.equals("")|| confirm.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();

                }else{
                    if(pass.equals(confirm)){
                        Boolean checkUser = DB.checkUsername(user);
                        if(checkUser == false){
                            Boolean insert = DB.addUser(user, pass, phoneNum);
                            if(insert == true){
                                Toast.makeText(RegisterActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}