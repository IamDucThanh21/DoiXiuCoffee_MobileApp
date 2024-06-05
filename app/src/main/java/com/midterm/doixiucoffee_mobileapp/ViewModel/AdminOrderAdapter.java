package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.table.setText("Bàn "+ listOrder.get(position).getTable());
        holder.totalCost.setText(listOrder.get(position).getTotalPrice()+".000");

        listDrink = listOrder.get(position).getListDrinks();
        orderAdapter = new OrderAdapter(listDrink, 1); //mode 1 là mode xem order của admin
        holder.rvDrink.setLayoutManager(new LinearLayoutManager(holder.rvDrink.getContext()));
        holder.rvDrink.setAdapter(orderAdapter);

    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView table;
        private TextView totalCost;
        private RecyclerView rvDrink;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            table = (TextView) itemView.findViewById(R.id.table);
            totalCost = (TextView) itemView.findViewById(R.id.total_cost);
            rvDrink = (RecyclerView) itemView.findViewById(R.id.rv_drink);

        }
    }
}
