package com.midterm.doixiucoffee_mobileapp.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.R;

import java.util.ArrayList;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {
    private ArrayList<Drink> listDrink;
    public DrinkAdapter(ArrayList<Drink> listDrink){this.listDrink = listDrink;}


    @NonNull
    @Override
    public DrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.ViewHolder holder, int position) {
        holder.drinkName.setText(listDrink.get(position).getDrinkName());
    }

    @Override
    public int getItemCount() {
        return listDrink.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView drinkName;
        public TextView priceS;
        public TextView priceB;
        public ViewHolder(View view){
            super(view);
            drinkName = (TextView) view.findViewById(R.id.drinkName);
            priceS = (TextView) view.findViewById(R.id.priceS);
            priceB = (TextView) view.findViewById(R.id.priceB);
        }
    }

}
