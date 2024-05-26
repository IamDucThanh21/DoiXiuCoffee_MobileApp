package com.midterm.doixiucoffee_mobileapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.User;
import com.midterm.doixiucoffee_mobileapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

//        addNewUser(generateRandomId(), "Chau Huy", "0123456", 0, "user");
    }

    public void addNewUser(String id, String name, String phone, int point, String role){
        Map<String, Object> dataUser = new HashMap<>();
        dataUser.put("name", name);
        dataUser.put("phone", phone);
        dataUser.put("point", point);
        dataUser.put("role", role);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("User").document(id).set(dataUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Debug", "Add success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Debug", "Add fail");
                    }
                });
    }

//    public static String generateRandomId() {
//        Random random = new Random();
//        int number = random.nextInt(100); // Tạo số ngẫu nhiên từ 0 đến 99
//        String randomId = "us" + String.format("%02d", number); // Đảm bảo số có hai chữ số
//        ArrayList<User> listUser = new ArrayList<>();
//        listUser = DataPerson.getInstance().getAllUser();
//
//        for (User user : listUser){
//            while (randomId == user.getIdUser()){
//                randomId = "us" + String.format("%02d", random.nextInt(100));
//            }
//        }
//        return randomId;
//    }
}