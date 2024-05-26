package com.midterm.doixiucoffee_mobileapp.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataOrder {
    private ArrayList<Order> allOrder;
    private static DataOrder dataOrder;
    private static Order order;

    public DataOrder() {}

    public static DataOrder getInstance(){
        if(dataOrder == null){
            dataOrder = new DataOrder();
        }
        return dataOrder;
    }

    public void getDataOrder(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if(order == null){
            order = new Order();
        }

        db.collection("Order").get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            allOrder = new ArrayList<>();
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Order order1 = new Order();
                                order1 = getOrder(document);
                                allOrder.add(order1);
                            }
                        }
                    }
                });
    }

    public Order getOrder(QueryDocumentSnapshot document){
        Order order1 = new Order();
        order1.setIdOrder(document.getId());
        order1.setDiscount(((Long)document.get("discount")).intValue());
        order1.setStatus((Boolean) document.get("status"));
        order1.setUser(DataPerson.getInstance().getUserById(document.get("idUser").toString()));
        ArrayList<Drink> drinks = new ArrayList<>();
        List<Object> i = (List<Object>) document.get("listDrink");
        for (Object dr :i){
            Map<String, String> dr1 = (Map<String, String>) dr;
            int size = Integer.parseInt(dr1.get("size"));
            String idDrink = dr1.get("idDrink");

            drinks.add(new Drink(idDrink, size));
        }
        order1.setListDrinks(drinks);
        order1.setTotalPrice(((Long) document.get("totalPrice")).intValue());
        return order1;
    }

//    public void addOrder(Order order){
//        this.AllOrder.add(order);
//    }

    public Order getOrder(){ return order;}
    public void setOrder(Order order){
        this.order = order;
    }

    public void updateTotalPrice(){
        int sum=0;
        if(order.getListDrinks().size()>0)
            for(Drink dr : order.getListDrinks()){
                sum+= dr.getSizeInfo().getPrice();
            }
        order.setTotalPrice(sum);
    }
}
