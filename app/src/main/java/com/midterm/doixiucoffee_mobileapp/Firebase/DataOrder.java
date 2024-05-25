package com.midterm.doixiucoffee_mobileapp.Firebase;

import com.midterm.doixiucoffee_mobileapp.Model.Order;

import java.util.ArrayList;

public class DataOrder {
    private ArrayList<Order> AllOrder;
    private static DataOrder dataOrder;

    public DataOrder() {
        AllOrder = new ArrayList<>();
    }

    public static DataOrder getInstance(){
        if(dataOrder == null){
            dataOrder = new DataOrder();
        }
        return dataOrder;
    }
    public void addOrder(Order order){
        this.AllOrder.add(order);
    }
}
