package com.example.afinal.User;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.afinal.Database;
import com.example.afinal.Model.Category;
import com.example.afinal.Model.order;
import com.example.afinal.R;

import java.util.List;

public class OrderFragment extends Fragment
{
    private List<order> orders;
    private int currentPosition;
    private Button loadOrderButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView orderName = view.findViewById(R.id.OrderNameEditText);
        loadOrderButton = view.findViewById(R.id.loadOrderButton);
        loadOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getContext());
                orders = db.getAllOrders();
                currentPosition = 0;

                // Set the order details in the text view
                StringBuilder orderDetails = new StringBuilder();
                if (orders != null && orders.size() > 0) {
                    order currentOrder = orders.get(currentPosition);
                    orderDetails.append(currentOrder.getOrderName()).append("\n")
                            .append("$").append(currentOrder.getOrderPrice()).append("\n")
                            .append("Quantity: ").append(currentOrder.getQuantity()); // Append quantity
                } else {
                    orderDetails.append("No orders found.");
                }
                orderName.setText(orderDetails.toString());
            }
        });

        // Set a click listener for the next category button
        Button nextCategoryButton = view.findViewById(R.id.nextCategoryButton);
        nextCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orders != null && orders.size() > 0) {
                    currentPosition++;
                    if (currentPosition >= orders.size()) {
                        currentPosition = 0;
                    }
                    orderName.setText(orders.get(currentPosition).getOrderName());
                }
            }
        });




    }

}