package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.afinal.Adapter.ProductAdapter;
import com.example.afinal.Adapter.ProductCategoryAdapter;
import com.example.afinal.Model.Category;
import com.example.afinal.Model.Products;
import com.example.afinal.User.LoginActivity;
import com.example.afinal.User.UserDash;

import java.util.ArrayList;
import java.util.List;

public class UserProducts extends AppCompatActivity {

    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_products);
        Button btnnext = findViewById(R.id.button);
        Database db = new Database(this);
        List<Category> productCategoryList = new ArrayList<>();
        productCategoryList.add(new Category(1, "Trending"));
        productCategoryList.add(new Category(2, "Most Popular"));
        productCategoryList.add(new Category(3, "All Body Products"));
        productCategoryList.add(new Category(4, "Skin Care"));
        productCategoryList.add(new Category(5, "Hair Care"));
        productCategoryList.add(new Category(6, "Make Up"));
        productCategoryList.add(new Category(7, "Fragrance"));

        for (Category category : productCategoryList) {
            db.addCategory(category.getName());
        }
        setProductRecycler(productCategoryList);

        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(1, "Japanese Cherry Blossom", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "African Mango Shower Gel", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Japanese Cherry Blossom", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "African Mango Shower Gel", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Japanese Cherry Blossom", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "African Mango Shower Gel", "350 ml", "$ 25.00", R.drawable.prod1));
        setProdItemRecycler(productsList);


        Button logoutButton = findViewById(R.id.Logout);
        // Attach an OnClickListener to handle the logout action
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the SharedPreferences object
                SharedPreferences preferences = getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE);
                // Get the SharedPreferences.Editor object to modify the preferences
                SharedPreferences.Editor editor = preferences.edit();
                // Set the "IS_USER_LOGGED_IN" preference to false to indicate that the user is not logged in
                editor.putBoolean("IS_USER_LOGGED_IN", false);
                // Apply the changes to the preferences
                editor.apply();
                Toast.makeText(UserProducts.this, "You have been logged out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserProducts.this, LoginActivity.class);
                startActivity(intent);
                // Finish the activity to return to the login screen or previous activity
                finish();
            }
        });


        Button userDashButton = findViewById(R.id.button);
        userDashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProducts.this, UserDash.class);
                startActivity(intent);
            }
        });


    }

    private void setProductRecycler(List<Category> productCategoryList) {

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);

    }

    private void setProdItemRecycler(List<Products> productsList) {

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productsList);
        prodItemRecycler.setAdapter(productAdapter);

    }






}

