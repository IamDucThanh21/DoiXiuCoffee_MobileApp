package com.midterm.doixiucoffee_mobileapp.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.midterm.doixiucoffee_mobileapp.Model.SizeInfo;

import java.util.ArrayList;
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
        data.put("drinkName", "Cà phê sữa đá");
        data.put("type", "Lớn");

        database.collection("Drink").document("Cà phê sữa").set(data)
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

    public static void readDocumentFromFireStore(){
        new Firestore();
        database.collection("Drink").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // Lặp qua danh sách các tài liệu
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    // Lấy tên của tài liệu
                    String documentName = documentSnapshot.getId();
                    // Sử dụng tên của tài liệu
                    Log.d("Debug", "Document name: " + documentName);
                }
            }
        });
    }

    public static ArrayList<SizeInfo> getFullSize(){
        ArrayList<String> docSizeInfo = new ArrayList<>();
        ArrayList<SizeInfo> fullSize = new ArrayList<>();
        new Firestore();
        database.collection("SizeInfo").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    String docSize = documentSnapshot.getId();
                    Log.d("list", docSize);
                    docSizeInfo.add(docSize);
                }
                for(String docSize : docSizeInfo){
                    fullSize.add(getSizeInfo(docSize));
                }
            }
        });

        return fullSize;
    }
    public static SizeInfo getSizeInfo(String id){
        new Firestore();
        SizeInfo sizeInfo = new SizeInfo("", 0,"");
        database.collection("SizeInfo").document(id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String size = documentSnapshot.getString("name");
                            int price = Math.toIntExact(documentSnapshot.getLong("price"));
                            String idDrink = documentSnapshot.getString("idDrink");

                            Log.d("Name: ", size);
                            sizeInfo.setSize(size);
                            sizeInfo.setPrice(price);
                            sizeInfo.setIdDrink(idDrink);
                            Log.d("size", sizeInfo.getSize());
                        }
                    }
                });
        return sizeInfo;

    }
}