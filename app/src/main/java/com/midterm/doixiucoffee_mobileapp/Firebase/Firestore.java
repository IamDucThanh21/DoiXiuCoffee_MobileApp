package com.midterm.doixiucoffee_mobileapp.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Firestore {
    private static FirebaseFirestore database;

    Firestore(){
        database = FirebaseFirestore.getInstance();
    }

    public static void writeData(){
        new Firestore();
        Map<String, Object> data = new HashMap<>();
        data.put("price", 20000);
        data.put("drinkName", "Hehe");
        data.put("type", "Hihi");

        database.collection("Drink").document("Test").set(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Log.d("Debug", "Write Successful");
                        else
                            Log.d("Debug", "Write fail");
                    }
                });
    }

    public static void readData(String collection, String docPath){
        new Firestore();
        DocumentReference docRef = database.collection(collection).document(docPath);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    int price = Math.toIntExact(documentSnapshot.getLong("price"));
                    String drinkName = documentSnapshot.getString("drinkName");
                    String type = documentSnapshot.getString("type");

                    Log.d("Debug", "price: " + price);
                    Log.d("Debug", "type: " + type);
                    Log.d("Debug", "drink name: " + drinkName);
                }
            }
        });
    }
}