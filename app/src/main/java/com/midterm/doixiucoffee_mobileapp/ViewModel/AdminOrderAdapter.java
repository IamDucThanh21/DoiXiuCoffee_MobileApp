package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.Order;
import com.midterm.doixiucoffee_mobileapp.R;

import java.util.ArrayList;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.ViewHolder> {

    private ArrayList<Order> listOrder;
    private ArrayList<Drink> listDrink;
    private OrderAdapter orderAdapter;

    public AdminOrderAdapter(ArrayList<Order> listOrder){
        this.listOrder = listOrder;
    }
    @NonNull
    @Override
    public AdminOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminOrderAdapter.ViewHolder holder, int position) {

        int i = position;
        //Setup ban đầu
        holder.table.setText("Bàn "+ listOrder.get(i).getTable()+" - ");
        holder.totalCost.setText(listOrder.get(i).getTotalPrice()+".000");
        holder.userName.setText(listOrder.get(i).getUser().getName());

        //Setup danh sách drink
        listDrink = listOrder.get(i).getListDrinks();
        orderAdapter = new OrderAdapter(listDrink, 1); //mode 1 là mode xem order của admin
        holder.rvDrink.setLayoutManager(new LinearLayoutManager(holder.rvDrink.getContext()));
        holder.rvDrink.setAdapter(orderAdapter);

        //Set sự kiện cho các nút
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataOrder.getInstance().getOrderByIdOrder(listOrder.get(i).getIdOrder()).setStatus("cancel");
                DataOrder.getInstance().setStatusFirebase(listOrder.get(i).getIdOrder(), "cancel");
                Navigation.findNavController(v).navigate(R.id.adminBookingFragment);
            }
        });
        holder.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataOrder.getInstance().getOrderByIdOrder(listOrder.get(i).getIdOrder()).setStatus("done");
                DataOrder.getInstance().setStatusFirebase(listOrder.get(i).getIdOrder(), "done");
                Navigation.findNavController(v).navigate(R.id.adminBookingFragment);
            }
        });

        if(listOrder.get(i).getStatus().equals("cancel")){
            holder.btnDone.setVisibility(View.GONE);
            holder.btnEdit.setVisibility(View.GONE);
            holder.btnRemove.setClickable(false);
        }

        if(listOrder.get(i).getStatus().equals("done")){
            holder.btnRemove.setVisibility(View.GONE);
            holder.btnEdit.setVisibility(View.GONE);
            holder.btnDone.setClickable(false);
        }

    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView table;
        private TextView totalCost;
        private RecyclerView rvDrink;
        private TextView userName;
        private RelativeLayout btnRemove;
        private RelativeLayout btnEdit;
        private RelativeLayout btnDone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            table = (TextView) itemView.findViewById(R.id.table);
            totalCost = (TextView) itemView.findViewById(R.id.total_cost);
            rvDrink = (RecyclerView) itemView.findViewById(R.id.rv_drink);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            btnRemove = (RelativeLayout) itemView.findViewById(R.id.btn_remove);
            btnEdit = (RelativeLayout) itemView.findViewById(R.id.btn_edit);
            btnDone = (RelativeLayout)  itemView.findViewById(R.id.btn_done);

        }
    }
}
