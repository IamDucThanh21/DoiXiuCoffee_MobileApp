package com.midterm.doixiucoffee_mobileapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.midterm.doixiucoffee_mobileapp.Firebase.Firestore;
import com.midterm.doixiucoffee_mobileapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Firestore database;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

    }
}