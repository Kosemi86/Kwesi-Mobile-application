package com.example.afinal.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.Database;
import com.example.afinal.Model.order;
import com.example.afinal.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<order> orderList;
    private Database database;


    public OrderAdapter(List<order> orderList, Database database) {
        this.orderList = orderList;
        this.database = database; // Assign the database object
    }



    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new OrderViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        order order = orderList.get(holder.getAdapterPosition());

        // Bind the order data to the views in the ViewHolder
        holder.nameTextView.setText(order.getOrderName());
        holder.priceTextView.setText(String.valueOf(order.getOrderPrice()));
        holder.quantityTextView.setText(String.valueOf(order.getQuantity()));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    order order = orderList.get(adapterPosition);
                    int orderId = order.getId();
                    database.deleteOrder(orderId);

                    // Remove the order from the list
                    orderList.remove(adapterPosition);

                    // Notify the adapter about the item removal
                    notifyItemRemoved(adapterPosition);
                }
            }
        });
    }


        @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        TextView quantityTextView;
        ImageView deleteButton;

        public OrderViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
