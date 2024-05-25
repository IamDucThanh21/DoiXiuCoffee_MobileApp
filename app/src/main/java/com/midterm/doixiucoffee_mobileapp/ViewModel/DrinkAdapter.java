package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.R;

import java.util.ArrayList;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {
    private ArrayList<Drink> listDrink;
    private int mode = 0;

    public DrinkAdapter(ArrayList<Drink> listDrink, int mode){
        this.listDrink = listDrink;
        this.mode = mode;
    }

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
        holder.priceS.setText(listDrink.get(position).getSizeInfos().get(0).getPrice()+"K");
        holder.priceB.setText(listDrink.get(position).getSizeInfos().get(1).getPrice()+"K");
        if(mode == 1){
            holder.borderB.setVisibility(View.VISIBLE);
            holder.borderS.setVisibility(View.VISIBLE);

            holder.priceS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.borderS.getVisibility()==View.VISIBLE){
                        holder.borderS.setVisibility(View.GONE);
                        holder.backS.setBackgroundColor(v.getResources().getColor(R.color.green));
                        holder.priceS.setTextColor(v.getResources().getColor(R.color.white));
                    }
                    else{
                        holder.borderS.setVisibility(View.VISIBLE);
                        holder.backS.setBackgroundColor(v.getResources().getColor(R.color.white));
                        holder.priceS.setTextColor(v.getResources().getColor(R.color.gray_text));
                    }
                }
            });
            holder.priceB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.borderB.getVisibility()==View.VISIBLE){
                        holder.borderB.setVisibility(View.GONE);
                        holder.backB.setBackgroundColor(v.getResources().getColor(R.color.green));
                        holder.priceB.setTextColor(v.getResources().getColor(R.color.white));
                    }
                    else{
                        holder.borderB.setVisibility(View.VISIBLE);
                        holder.backB.setBackgroundColor(v.getResources().getColor(R.color.white));
                        holder.priceB.setTextColor(v.getResources().getColor(R.color.gray_text));
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listDrink.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView drinkName;
        public TextView priceS;
        public TextView priceB;
        public ShapeableImageView borderS;
        public ShapeableImageView borderB;
        public ShapeableImageView backS;
        public ShapeableImageView backB;
        public ViewHolder(View view){
            super(view);
            drinkName = (TextView) view.findViewById(R.id.drinkName);
            priceS = (TextView) view.findViewById(R.id.priceS);
            priceB = (TextView) view.findViewById(R.id.priceB);
            borderS = (ShapeableImageView) view.findViewById(R.id.borderS);
            borderB = (ShapeableImageView) view.findViewById(R.id.borderB);
            backS = (ShapeableImageView) view.findViewById(R.id.backS);
            backB = (ShapeableImageView) view.findViewById(R.id.backB);
        }
    }

}
