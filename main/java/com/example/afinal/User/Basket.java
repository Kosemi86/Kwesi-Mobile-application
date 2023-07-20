package com.example.afinal.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.afinal.Adapter.OrderAdapter;
import com.example.afinal.Database;
import com.example.afinal.Model.order;
import com.example.afinal.Productdetails;
import com.example.afinal.R;

import java.util.List;
public class Basket extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recyclerView = findViewById(R.id.recyclerView);
        Button btnBack = findViewById(R.id.btnBack);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = new Database(this);

        // Get all orders from the database
        List<order> orderList = database.getAllOrders();

        // Display the orders in the RecyclerView
        orderAdapter = new OrderAdapter(orderList, database); // Pass the database object to the adapter
        recyclerView.setAdapter(orderAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basket.this, Productdetails.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
