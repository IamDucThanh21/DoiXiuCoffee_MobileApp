package com.midterm.doixiucoffee_mobileapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.midterm.doixiucoffee_mobileapp.databinding.BookingLayoutBinding;

public class BookingLayout extends AppCompatActivity {
    BookingLayoutBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BookingLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }

}
