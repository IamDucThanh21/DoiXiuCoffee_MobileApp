package com.midterm.doixiucoffee_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.midterm.doixiucoffee_mobileapp.Firebase.Firestore;
import com.midterm.doixiucoffee_mobileapp.Model.SizeInfo;
import com.midterm.doixiucoffee_mobileapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Firestore database;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

//        database = new Firestore();
//        Log.d("test", fetchData("SizeInfo", "si01").getIdDrink());
    }
}