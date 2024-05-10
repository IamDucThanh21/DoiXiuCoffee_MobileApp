package com.midterm.doixiucoffee_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.midterm.doixiucoffee_mobileapp.View.BookingLayout;
import com.midterm.doixiucoffee_mobileapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.openBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookingLayout.class);
                startActivity(intent);
            }
        });
    }

}