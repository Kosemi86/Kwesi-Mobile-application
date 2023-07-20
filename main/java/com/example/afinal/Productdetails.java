package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.User.Basket;

public class Productdetails extends AppCompatActivity {

    private TextView quantityTextView;
    private ImageView minusImageView;
    private ImageView plusImageView;

    private int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        Database db = new Database(this);
        quantityTextView = findViewById(R.id.quantityTextView);
        minusImageView = findViewById(R.id.imageView8);
        plusImageView = findViewById(R.id.imageView9);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Productdetails.this, UserProducts.class);
                startActivity(intent);
            }
        });

        minusImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    quantityTextView.setText(String.valueOf(quantity));
                }
            }
        });


        plusImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityTextView.setText(String.valueOf(quantity));
            }
        });
        TextView Name = findViewById(R.id.textView11);
        TextView Price = findViewById(R.id.textView12);
        Button addToCartButton = findViewById(R.id.button2);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String orderName = Name.getText().toString();
                String orderPrice = Price.getText().toString();
                int orderQuantity = Integer.parseInt(quantityTextView.getText().toString()); // Get the quantity from the quantity TextView

                // Add the order to the database
                db.AddOrder(orderName, orderPrice, orderQuantity);
                // Start the BasketActivity
                Intent intent = new Intent(Productdetails.this, Basket.class);
                startActivity(intent);
                // Show a toast message to indicate that the order has been added
                Toast.makeText(getApplicationContext(), "Order added to cart!", Toast.LENGTH_SHORT).show();
            }
        });



    }







}