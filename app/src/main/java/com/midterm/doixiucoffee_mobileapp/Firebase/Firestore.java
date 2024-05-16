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
import java.util.concurrent.ExecutionException;

public class Firestore {
    private static FirebaseFirestore database;

    public Firestore(){
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

    public void getListDocumentFromCollection(String collection ,final FirestoreCallbackString callback){
        database.collection(collection).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            ArrayList<String> documentNameList = new ArrayList<>();
                            for(QueryDocumentSnapshot document : task.getResult()){
                                documentNameList.add(document.getId());
                            }
                            callback.onCallback(documentNameList);
                        }else {
                            callback.onCallback(null);
                        }
                    }
                });
    }

    public interface FirestoreCallbackString {
        void onCallback(ArrayList<String> list);
    }

    public void getDataSizeInfoFromDocument(String collection, String document, final FirestoreCallbackDataSizeInfo callback){
        SizeInfo sizeInf = new SizeInfo("", 0, "");
        database.collection(collection).document(document).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String idDrink = documentSnapshot.getString("idDrink");
                            String size = documentSnapshot.getString("name");
                            int price = Math.toIntExact(documentSnapshot.getLong("price"));

                            sizeInf.setSize(size);
                            sizeInf.setPrice(price);
                            sizeInf.setIdDrink(idDrink);
                        }
                        callback.onCallback(sizeInf);
                    }
                });
    }

    public interface FirestoreCallbackDataSizeInfo{
        void onCallback(SizeInfo sizeInfo);
    }
}