package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Model.Order;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.AdminOrderAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentAdminBookingBinding;

import java.util.ArrayList;

public class AdminBookingFragment extends Fragment {
    private ArrayList<Order> listOrder;
    private AdminOrderAdapter adminOrderAdapter;

    private FragmentAdminBookingBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_admin_booking, null, false);

        binding.includeHb.homeBackTitle.setText("Danh sách gọi món");
        binding.toolbar.imgAva.setVisibility(View.VISIBLE);
        binding.toolbar.btnLogin.setVisibility(View.GONE);

        listOrder = new ArrayList<>();
        listOrder.add(DataOrder.getInstance().getOrder());
        adminOrderAdapter = new AdminOrderAdapter(listOrder);
        binding.rvOrder.setAdapter(adminOrderAdapter);
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });

        View view = binding.getRoot();
        return view;
    }
}