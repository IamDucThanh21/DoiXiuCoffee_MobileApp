package com.midterm.doixiucoffee_mobileapp.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.midterm.doixiucoffee_mobileapp.Model.Feedback;
import com.midterm.doixiucoffee_mobileapp.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataFeedback {
    private ArrayList<Feedback> listFeedback;
    private static DataFeedback dataFeedback;
    private DataFeedback(){}
    public static DataFeedback getInstance(){
        if (dataFeedback == null){
            dataFeedback = new DataFeedback();
        }
        return dataFeedback;
    }

    public ArrayList<Feedback> getListFeedback() {
        return listFeedback;
    }

    public void getAllFeedback(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Feedback").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            listFeedback = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()){
                                listFeedback.add(getDataFeedback(document));
                            }
                        }
                    }
                });
    }

    public Feedback getDataFeedback(QueryDocumentSnapshot document){
        Feedback fb = new Feedback();
        fb.setIdFeedback(document.getId());
        fb.setContent((String) document.get("content"));
        fb.setDate(document.getTimestamp("date"));
        User user;
        user = DataPerson.getInstance().getUserById((String) document.get("idUser"));
        fb.setUser(user);
        fb.setPublic((Boolean) document.get("isPublic"));
        fb.setIncognito((Boolean) document.get("incognito"));
        return fb;
    }

    public void addNewFeedback(String idUser, Timestamp date, String content, Boolean incognito, Boolean isPublic){
        Map<String, Object> dataFeedback = new HashMap<>();
        dataFeedback.put("content", content);
        dataFeedback.put("date", date);
        dataFeedback.put("idUser", idUser);
        dataFeedback.put("incognito", incognito);
        dataFeedback.put("isPublic", isPublic);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Feedback").add(dataFeedback)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("add feedback", "Success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("add feedback", "Fail");
                    }
                });
    }

}
