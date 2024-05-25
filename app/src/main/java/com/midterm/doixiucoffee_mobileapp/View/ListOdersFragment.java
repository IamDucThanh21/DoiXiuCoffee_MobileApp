package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midterm.doixiucoffee_mobileapp.Model.Order;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.OrderAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentListOdersBinding;

import java.util.ArrayList;

public class ListOdersFragment extends Fragment {

    private FragmentListOdersBinding binding;
    private ArrayList<Order> listOrder;
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

        listOrder = new ArrayList<>();
        Order o = new Order();
        listOrder.add(o);
        listOrder.add(o);

        orderAdapter = new OrderAdapter(listOrder);
        binding.rvOrder.setAdapter(orderAdapter);

        Log.d("test", "Size oder:"+orderAdapter.getItemCount());

        View view = binding.getRoot();
        // Inflate the layout for this fragment
        return view;
    }
}