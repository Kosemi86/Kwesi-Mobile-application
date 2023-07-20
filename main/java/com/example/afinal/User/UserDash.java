package com.example.afinal.User;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.afinal.Admin.AdminFragment;
import com.example.afinal.Database;
import com.example.afinal.Model.Category;
import com.example.afinal.R;

import java.util.List;


public class UserDash extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user);

        Button addCategoryButton = findViewById(R.id.view_categories_button);
        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the admin fragment with add category view
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new UserFragment())
                        .commit();
            }
        });


        Button vieworderbutton = findViewById(R.id.view_order_button);
        vieworderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the admin fragment with add category view
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new OrderFragment())
                        .commit();
            }
        });
    }
}