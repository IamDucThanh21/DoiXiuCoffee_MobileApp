package com.midterm.doixiucoffee_mobileapp.View;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.databinding.BookingLayoutBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BookingLayout extends AppCompatActivity {
    private BookingLayoutBinding binding;
    private ArrayList<Category> listCategory;
    private CategoryAdapter categoryAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BookingLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this));
        listCategory = new ArrayList<Category>();
        categoryAdapter = new CategoryAdapter(listCategory);
        binding.rvCategory.setAdapter(categoryAdapter);

        ArrayList<Drink> listDrink = new ArrayList<Drink>();
        listDrink.add(new Drink("dr01", "Phin đen","ca01", 'S', 15,"hehehe"));
        listDrink.add(new Drink("dr02", "Phin sữa","ca01", 'S', 15,"hehehe"));

        listCategory.add(new Category("ca01", "Cà phê", listDrink, "Đây là thông tin."));
        listCategory.add(new Category("ca02", "Trà", listDrink, "Đây là thông tin."));

    }

}
