package com.midterm.doixiucoffee_mobileapp.Firebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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
import com.midterm.doixiucoffee_mobileapp.Model.Admin;
import com.midterm.doixiucoffee_mobileapp.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
                            DataFeedback.getInstance().getAllFeedback();
                        }
                    }
                });
    }

    public void addNewUser(String name, String phone, int point, String role) {
        Map<String, Object> dataUser = new HashMap<>();
        dataUser.put("name", name);
        dataUser.put("phone", phone);
        dataUser.put("point", point);
        dataUser.put("role", role);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User").add(dataUser)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Debug", "Add success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Debug", "Add fail", e);
                    }
                });
    }

    public Admin getDataAdmin(QueryDocumentSnapshot document){
        Admin admin = new Admin();
        admin.setIdPerson(document.getId());
        admin.setPassword((String) document.get("password"));
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
        user.setImage((String) document.get("image"));

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

    public void uploadImageToFirestore(String base64String){
        Map<String, Object> image = new HashMap<>();
        image.put("image", base64String);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User").document(getIdPersonLogin()).update(image)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Debug", "Update image success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Debug", "Update image fail");
                    }
                });
    }

    public String getIdPersonLogin() {
        return idPersonLogin;
    }

    public void setIdPersonLogin(String idPersonLogin) {
        this.idPersonLogin = idPersonLogin;
    }

    public Bitmap base64toBitmap(String base64String){
        // Giải mã chuỗi Base64 thành mảng byte
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        // Chuyển đổi mảng byte thành đối tượng Bitmap
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
    public boolean isAdminById(String id){
        for(Admin admin: allAdmin){
            if(id.equals(admin.getIdPerson())){
                return true;
            }
        }
        return false;
    }

    public boolean checkPhone(String phone){
        for (User user : allUser){
            if (user.getPhoneNumber().equals(phone)) return false;
        }
        for (Admin admin : allAdmin){
            if (admin.getPhoneNumber().equals(phone)) return false;
        }
        return true;
    }
}

