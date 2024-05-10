package com.midterm.doixiucoffee_mobileapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.doixiucoffee_mobileapp.MainActivity;
import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.SizeInfo;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.View.Adapter.CategoryAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.BookingLayoutBinding;

import java.util.ArrayList;

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

        binding.includeHb.homeBackTitle.setText(R.string.booking);

        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this));
        listCategory = new ArrayList<Category>();
        categoryAdapter = new CategoryAdapter(listCategory);
        binding.rvCategory.setAdapter(categoryAdapter);

        khoitaodulieumau();

        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingLayout.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void khoitaodulieumau(){
        ArrayList<SizeInfo> sizePhinden = new ArrayList<>();
        sizePhinden.add(new SizeInfo("M", 12));
        sizePhinden.add(new SizeInfo("L", 15));

        ArrayList<SizeInfo> sizePhinsua = new ArrayList<>();
        sizePhinsua.add(new SizeInfo("M", 15));
        sizePhinsua.add(new SizeInfo("L", 18));

        ArrayList<Drink> listDrink = new ArrayList<>();
        listDrink.add(new Drink("dr01", "Phin đen", "ca01", sizePhinden, "Cau chuyen"));
        listDrink.add(new Drink("dr02", "Phin sữa", "ca01", sizePhinsua, "Cau chuyen"));

        listCategory.add(new Category("ca01", "Cà phê", listDrink, "Đây là thông tin"));
    }

}
