package com.midterm.doixiucoffee_mobileapp.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.OrderAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentListOdersBinding;

import java.util.ArrayList;

public class ListOdersFragment extends Fragment {

    private FragmentListOdersBinding binding;
    private OrderAdapter orderAdapter;

    private ArrayList<Drink> listDrink;
    private int size;

    //Khởi tạo các hàm dựng để đẩy vào một fragment khác
    public ListOdersFragment() {}
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


        //Set up danh sách các món đã chọn
        listDrink = DataOrder.getInstance().getOrder().getListDrinks();
        orderAdapter = new OrderAdapter(listDrink, 0);
        binding.rvOrder.setAdapter(orderAdapter);
        Log.d("test", "Size oder:" + orderAdapter.getItemCount());
        binding.totalCost.setText(DataOrder.getInstance().getOrder().getTotalPrice()+".000");


        //Kiểm tra xem user này đã có order trong hàng đợi chưa, nếu đã có thì đợi.
        if(DataOrder.getInstance().getOrder().getStatus().equals("waiting")){
            binding.table.setEnabled(false);
            binding.btnBookNow.setClickable(false);
            binding.btnBookNow.setBackgroundColor(getResources().getColor(R.color.gray));
            binding.btnBookNow.setText("Đợi quầy!");
        }


        //Setup sự kiện nút đặt ngay
        binding.btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext()); // 'this' là context của Activity hoặc Fragment
                builder.setTitle("Thông báo")
                        .setMessage("Bạn cần nhập số bàn!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                //Nếu chưa nhập số bàn thì thông báo
                if(binding.table.getText().toString().equals("")){
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                //Nếu chưa chọn đồ uống thì thông báo
                else if(listDrink.size()==0){
                    builder.setMessage("Bạn cần chọn ít nhất 1 đồ uống!");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                else{
                    DataOrder.getInstance().getOrder().setStatus("waiting");
                    DataOrder.getInstance().addNewOrder(DataOrder.getInstance().getOrder());
                    Navigation.findNavController(v).navigate(R.id.bookingFragment);
                }
            }
        });

        //Setup và lưu số bàn cho order hiện tại, lưu khi mỗi lần chỉnh sửa
        int table = DataOrder.getInstance().getOrder().getTable();
        if(table!=-1){
            binding.table.setText(table+"");
        }
        binding.table.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ta = binding.table.getText().toString().trim();
                if(!ta.equals("")){
                    DataOrder.getInstance().getOrder().setTable(Integer.parseInt(ta));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Setup nút back
        binding.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.bookingFragment);
            }
        });

        View view = binding.getRoot();
        return view;
    }
}