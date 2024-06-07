package com.midterm.doixiucoffee_mobileapp.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        order1.setStatus(document.get("status").toString());
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
        order1.setTable(((Long) document.get("table")).intValue());
        return order1;
    }

//    public void addOrder(Order order){
//        this.AllOrder.add(order);
//    }

    public void addNewOrder(Order order){
        Map<String, Object> dataOrder = new HashMap<>();
        dataOrder.put("discount", order.getDiscount());
        dataOrder.put("idUser", DataPerson.getInstance().getIdPersonLogin());

        ArrayList<Map<String, String>> listDrinks = new ArrayList<>();
        for (Drink drink : order.getListDrinks()){
            Map<String, String> dataDrink = new HashMap<>();
            dataDrink.put("idDrink", drink.getIdDrink());

            if (Objects.equals(drink.getSizeInfo().getSize(), "M"))
                dataDrink.put("size", "0");
            else
                dataDrink.put("size", "1");


            listDrinks.add(dataDrink);
        }

        dataOrder.put("listDrink", listDrinks);
        dataOrder.put("status", order.getStatus());
        dataOrder.put("table", order.getTable());
        dataOrder.put("totalPrice", order.getTotalPrice());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Order").add(dataOrder)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Debug", "Add order success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Debug", "Add order fail");
                    }
                });
    }

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
    public ArrayList<Order> getAllOrder(){return allOrder;}

    public Order findOrderByIdUser(String idUser){
        for(Order o: allOrder){
            if(o.getUser().getIdPerson().equals(idUser)){
                return o;
            }
        }
        return null;
    }

    public void setStatusFirebase(String idOrder, String newStatus){

    }
}
