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
    private int mode = 0;

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

        //Setup ban đầu
        binding.includeHb.homeBackTitle.setText("Danh sách gọi món");
        binding.toolbar.imgAva.setVisibility(View.VISIBLE);
        binding.toolbar.btnLogin.setVisibility(View.GONE);

        //Setup danh sách order, chỉ chọn những order đang đợi
        listOrder = new ArrayList<>(DataOrder.getInstance().getAllOrder());
        ArrayList<Order> listWaitingOrder = new ArrayList<>();
        for(Order o: listOrder){
            if(o.getStatus().equals("waiting")){
                listWaitingOrder.add(o);
            }
        }
        adminOrderAdapter = new AdminOrderAdapter(listWaitingOrder);
        binding.rvOrder.setAdapter(adminOrderAdapter);
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(getContext()));

        //Gán sự kiện cho nút back
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });

        //Xem thoong tin cá nhân khi nhấn vào ava
        binding.toolbar.imgAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.infoUserFragment);
            }
        });

        binding.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mode==0){
                    if(binding.btnMoreCancel.getVisibility() == View.GONE){
                        binding.btnMoreCancel.setVisibility(View.VISIBLE);
                        binding.btnMoreDone.setVisibility(View.VISIBLE);

                    }
                    else {
                        binding.btnMoreCancel.setVisibility(View.GONE);
                        binding.btnMoreDone.setVisibility(View.GONE);
                    }
                }
                else{
                    Navigation.findNavController(v).navigate(R.id.adminBookingFragment);
                }
            }
        });

        binding.btnMoreCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Order> l = new ArrayList<>();
                for(Order o: listOrder){
                    if(o.getStatus().equals("cancel")){
                        l.add(o);
                    }
                }
                adminOrderAdapter = new AdminOrderAdapter(l);
                binding.rvOrder.setAdapter(adminOrderAdapter);

                binding.btnMoreCancel.setVisibility(View.GONE);
                binding.btnMoreDone.setVisibility(View.GONE);
                binding.ivMenu.setImageResource(R.drawable.backk_icon);
                binding.includeHb.homeBackTitle.setText("Các đơn đã hủy");
                mode = 1;
            }
        });

        binding.btnMoreDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Order> l = new ArrayList<>();
                for(Order o: listOrder){
                    if(o.getStatus().equals("done")){
                        l.add(o);
                    }
                }
                adminOrderAdapter = new AdminOrderAdapter(l);
                binding.rvOrder.setAdapter(adminOrderAdapter);

                binding.btnMoreCancel.setVisibility(View.GONE);
                binding.btnMoreDone.setVisibility(View.GONE);
                binding.ivMenu.setImageResource(R.drawable.backk_icon);
                binding.includeHb.homeBackTitle.setText("Các đơn hoàn thành");
                mode = 1;
            }
        });

        View view = binding.getRoot();
        return view;
    }
}