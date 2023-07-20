package com.example.afinal.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.Database;
import com.example.afinal.R;
import com.example.afinal.UserProducts;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Database db = new Database(this);

        // Check if user is already logged in
        if (isUserLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, UserProducts.class);
            startActivity(intent);
            finish();
        }

        mUsernameEditText = findViewById(R.id.username);
        mPasswordEditText = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.btnLogin);


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                boolean isValid = db.checkUserCredentials(username, password);
                if (isValid){
                    // User is authenticated, save login status and proceed to main activity
                    SharedPreferences preferences = getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("IS_USER_LOGGED_IN", true);
                    editor.putString("LOGGED_IN_USER_NAME", username);
                    editor.apply();
                    // User credentials are correct, go to main activity
                    Intent intent = new Intent(LoginActivity.this, UserProducts.class);
                    startActivity(intent);
                    finish(); // Finish the login activity so user can't go back to it
                    Toast.makeText(LoginActivity.this, username + "logged", Toast.LENGTH_SHORT).show();

                } else {
                    // Authentication failed, display error message
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView textView = findViewById(R.id.register);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    // Method to check if user is already logged in
    public boolean isUserLoggedIn() {
        SharedPreferences preferences = getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE);
        return preferences.getBoolean("IS_USER_LOGGED_IN", false);
    }
}