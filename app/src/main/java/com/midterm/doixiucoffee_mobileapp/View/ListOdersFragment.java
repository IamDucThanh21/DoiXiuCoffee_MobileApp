package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.Order;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.OrderAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentListOdersBinding;

import java.util.ArrayList;

public class ListOdersFragment extends Fragment {

    private FragmentListOdersBinding binding;
    private ArrayList<Drink> listDrink;
    private OrderAdapter orderAdapter;
    public ListOdersFragment() {
        // Required empty public constructor
    }

    public static ListOdersFragment newInstance() {
        ListOdersFragment fragment = new ListOdersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListOdersBinding.inflate(getLayoutInflater());

        listDrink = new ArrayList<Drink>();
        Drink d = new Drink();
        listDrink.add(d);
        listDrink.add(d);


        orderAdapter = new OrderAdapter(listDrink);
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.rvOrder.setAdapter(orderAdapter);



        Log.d("test", "Size oder:" + orderAdapter.getItemCount());

        View view = binding.getRoot();
        // Inflate the layout for this fragment
        return view;
    }
}