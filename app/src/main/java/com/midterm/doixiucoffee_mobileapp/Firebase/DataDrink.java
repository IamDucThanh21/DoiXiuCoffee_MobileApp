package com.midterm.doixiucoffee_mobileapp.Firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.SizeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDrink {
    private ArrayList<Category> menu;
    private static DataDrink firesbase;
    public static DataDrink getInstance(){
        if(firesbase == null){
            firesbase = new DataDrink();
        }
        return firesbase;
    }

    public DataDrink(){};

    public void getDataMenu(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Category").get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            menu = new ArrayList<>();
                            for(QueryDocumentSnapshot document: task.getResult()){
                                ArrayList<Drink> listDrink = new ArrayList<>(getDataDrink(document));
                                menu.add(new Category(document.getId(), document.get("name").toString(),listDrink, document.get("info").toString()));
                            }
                        }
                    }
                });
    }
    public ArrayList<Drink> getDataDrink(QueryDocumentSnapshot document){
        List<Object> i = (List<Object>) document.get("drink");
        ArrayList<Drink> listDrink = new ArrayList<>();

        for(Object dr : i){
            Map<String, Object> dr1 = (Map<String, Object>) dr;
            Map<String, Long> size = (Map<String, Long>) dr1.get("size");

            ArrayList<SizeInfo> listSize = new ArrayList<>();
            listSize.add(new SizeInfo("M", size.get("M").intValue(), dr1.get("name").toString() ));
            listSize.add(new SizeInfo("L", size.get("L").intValue(), dr1.get("name").toString() ));

            Drink drink = new Drink(dr1.get("idDrink").toString(), dr1.get("name").toString(),document.getId().toString(), listSize, dr1.get("story").toString() );
            listDrink.add(drink);
        }
        return listDrink;
    }

    public void addNewDrink(String idDrink, String drinkName, String typeDrink, ArrayList<SizeInfo> listSizeInfo, String story){
        Map<String, Object> drink = new HashMap<>();
        drink.put("idDrink", idDrink);
        drink.put("name", drinkName);
        drink.put("story", story);

        Map<String, Integer> sizeInfo = new HashMap<>();
        for (SizeInfo data : listSizeInfo){
            sizeInfo.put(data.getSize(), data.getPrice());
            sizeInfo.put(data.getSize(), data.getPrice());
        }
        drink.put("size", sizeInfo);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Category").document(typeDrink).update("drink", FieldValue.arrayUnion(drink));
    }

    public ArrayList<Category> getMenu(){
        return menu;
    }

    public Drink getDrinkById(String id){
        for (Category ca : menu){
            for (Drink dr : ca.getListDrink()){
                if(dr.getIdDrink().equals(id)){
                    return dr;
                }
            }
        }
        return null;
    }
}

