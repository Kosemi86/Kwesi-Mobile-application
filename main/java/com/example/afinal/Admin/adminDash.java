package com.example.afinal.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.R;

public class adminDash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin_dash);

        Button viewcategoriesbutton = findViewById(R.id.view_categories_button);
        viewcategoriesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the admin fragment with add category view
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new AdminFragment())
                        .commit();
            }
        });
    }
}
