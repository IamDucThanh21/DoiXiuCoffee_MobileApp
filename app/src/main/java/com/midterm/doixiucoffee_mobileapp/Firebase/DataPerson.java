package com.midterm.doixiucoffee_mobileapp.Firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.midterm.doixiucoffee_mobileapp.Model.Admin;
import com.midterm.doixiucoffee_mobileapp.Model.User;

import java.util.ArrayList;

public class DataPerson {

    private static DataPerson dataPerson;
    private ArrayList<Admin> allAdmin;
    private ArrayList<User> allUser;
    private static String idPersonLogin = "null";


    public DataPerson(){};
    public static DataPerson getInstance(){
        if(dataPerson == null){
            dataPerson = new DataPerson();
        }
        return dataPerson;
    }

    public ArrayList<Admin> getAllAdmin(){
        return allAdmin;
    }

    public ArrayList<User> getAllUser(){
        return allUser;
    }

    public void getDataPerson(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            allAdmin = new ArrayList<>();
                            allUser = new ArrayList<>();
                            for(QueryDocumentSnapshot document: task.getResult()){
                                if(document.get("role").equals("admin"))
                                    allAdmin.add(getDataAdmin(document));
                                else allUser.add(getDataUser(document));
                            }
                        }
                    }
                });
    }

    public Admin getDataAdmin(QueryDocumentSnapshot document){
        Admin admin = new Admin();
        admin.setIdPerson(document.getId());
        admin.setPhoneNumber((String) document.get("phone"));
        admin.setName((String) document.get("name"));
        return admin;
    }

    public User getDataUser(QueryDocumentSnapshot document){
        User user = new User();
        user.setIdPerson(document.getId());
        user.setName((String) document.get("name"));
        user.setPhoneNumber((String) document.get("phone"));
        user.setPoint(((Long) document.get("point")).intValue());

        return user;
    }

    public User getUserById(String id){
        for(User u : allUser){
            if(u.getIdPerson().equals(id)){
                return u;
            }
        }
        return null;
    }

    public String getIdPersonLogin() {
        return idPersonLogin;
    }

    public void setIdPersonLogin(String idPersonLogin) {
        this.idPersonLogin = idPersonLogin;
    }
}
