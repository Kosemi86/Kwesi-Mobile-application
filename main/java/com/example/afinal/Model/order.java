package com.example.afinal.Model;

public class order {
    private int id;
    private String orderName;
    private double orderPrice;
    private int OrderQuantity;

    public order(int id, String orderName, double orderPrice, int OrderQuantity) {
        this.id = id;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.OrderQuantity = OrderQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getQuantity() {
        return OrderQuantity;
    }

    public void setQuantity(int quantity) {
        this.OrderQuantity = quantity;
    }
}
