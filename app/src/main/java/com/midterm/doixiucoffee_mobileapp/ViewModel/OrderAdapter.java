package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataDrink;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.Order;
import com.midterm.doixiucoffee_mobileapp.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private ArrayList<Drink> listOrderDrink = DataOrder.getInstance().getOrder().getListDrinks();

    public OrderAdapter() {}

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.drinkName.setText(listOrderDrink.get(position).getDrinkName());
        holder.price.setText(listOrderDrink.get(position).getSizeInfo().getPrice()+"K");
        int i = position;

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = 0;
                String s = listOrderDrink.get(i).getSizeInfo().getSize();
                if(s.equals("L")) size = 1;
                DataOrder.getInstance().getOrder().removeDrink(listOrderDrink.get(i).getIdDrink(), size);
                DataOrder.getInstance().updateTotalPrice();
                Log.d("action","remove:");
                Navigation.findNavController(v).navigate(R.id.addDrinkFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOrderDrink.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView drinkName;
        private TextView price;
        private ImageView btnRemove;
        private ImageView btnTakeNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            drinkName = (TextView) itemView.findViewById(R.id.drinkName);
            price = (TextView)  itemView.findViewById(R.id.price);
            btnRemove = (ImageView) itemView.findViewById(R.id.btn_remove);
            btnTakeNote = (ImageView) itemView.findViewById(R.id.btn_take_note);
        }
    }
}
