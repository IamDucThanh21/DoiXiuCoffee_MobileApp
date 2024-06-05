package com.midterm.doixiucoffee_mobileapp.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataDrink;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.CategoryAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentBookingBinding;

import java.util.ArrayList;


public class BookingFragment extends Fragment {
    private ArrayList<Category> listCategory;
    private CategoryAdapter categoryAdapter;
    private FragmentBookingBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DataOrder.getInstance().getDataOrder();
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_booking, null, false);

        if(!DataPerson.getInstance().getIdPersonLogin().equals("null")) {
            binding.toolbar.imgAva.setVisibility(View.VISIBLE);
            binding.toolbar.btnLogin.setVisibility(View.GONE);
        }

        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listCategory = new ArrayList<>(DataDrink.getInstance().getMenu());
        if(listCategory.size()!=0){
            categoryAdapter = new CategoryAdapter(listCategory, 0);
            Log.d("Check","Size menu: "+categoryAdapter.getItemCount());
            binding.rvCategory.setAdapter(categoryAdapter);
        }

        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });



        binding.toolbar.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginFragment);
            }
        });

//        for (Order order : DataOrder.getInstance().allOrder){
//            Log.d("Order", order.getIdOrder());
//        }

        View view = binding.getRoot();
        return view;
    }

}