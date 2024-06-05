package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataDrink;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.Order;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.OrderAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentListOdersBinding;

import java.util.ArrayList;

public class ListOdersFragment extends Fragment {

    private FragmentListOdersBinding binding;
    private OrderAdapter orderAdapter;

    private ArrayList<Drink> listDrink;
    private int size;
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
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //Nếu size bằng 0 thì hiện chú thích chọn món
        size = DataOrder.getInstance().getOrder().getListDrinks().size();
        if(size == 0){
            binding.rvOrder.setVisibility(View.GONE);
            binding.rvNote.setVisibility(View.VISIBLE);
        }
        binding.numDrink.setText(size+"");

        listDrink = DataOrder.getInstance().getOrder().getListDrinks();

        orderAdapter = new OrderAdapter(listDrink, 0);
        binding.rvOrder.setAdapter(orderAdapter);

        Log.d("test", "Size oder:" + orderAdapter.getItemCount());

        binding.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.bookingFragment);
            }
        });

        binding.totalCost.setText(DataOrder.getInstance().getOrder().getTotalPrice()+".000");

        binding.btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            }
        });


        View view = binding.getRoot();
        // Inflate the layout for this fragment
        return view;
    }
}