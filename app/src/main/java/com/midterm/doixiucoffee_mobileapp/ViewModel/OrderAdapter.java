package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.doixiucoffee_mobileapp.Model.Order;
import com.midterm.doixiucoffee_mobileapp.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private ArrayList<Order> listOrder;

    public OrderAdapter(ArrayList<Order> listOrder) {
        this.listOrder = listOrder;
    }
    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.text.setText("Mot hai ba");
        Log.d("text2", "hehe");
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
