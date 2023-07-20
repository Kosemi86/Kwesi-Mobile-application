package com.example.afinal.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.afinal.Database;
import com.example.afinal.R;


public class Admin extends AppCompatActivity {

    private EditText UserEmail, UserPassword,Username;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        UserEmail = findViewById(R.id.email);
        UserPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnregister);
        Username = findViewById(R.id.username);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = UserEmail.getText().toString();
                String username = Username.getText().toString();
                String password = UserPassword.getText().toString();
                if(email.equals("Admin@gmail.com") && password.equals("Admin") && username.equals("Admin")){
                    Toast.makeText(Admin.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Admin.this, adminDash.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Admin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
