package com.example.afinal.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.Admin.Admin;
import com.example.afinal.Database;
import com.example.afinal.R;

public class MainActivity extends AppCompatActivity {
    private Button mAdminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText UserFullname = findViewById(R.id.username);
        EditText UserPassword = findViewById(R.id.password);
        EditText UserEmail = findViewById(R.id.email);
        Button btnRegister = findViewById(R.id.btnregister);

        mAdminButton = findViewById(R.id.Admin);

        Database db = new Database(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserFullname = UserFullname.getText().toString();
                String strPassword = UserPassword.getText().toString();
                String strEmail = UserEmail.getText().toString();
                if(strUserFullname.isEmpty() || strPassword.isEmpty() || strEmail.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Empty feilds", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.AddUser(strUserFullname, strPassword,strEmail);
                    Toast.makeText(MainActivity.this, strUserFullname + "You have been registerd", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            }
        });

        TextView textView = findViewById(R.id.text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        mAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivity(intent);
                finish();
            }
        });
    }


}

