package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<Category> listCategory;
    private DrinkAdapter drinkAdapter;
    private ArrayList<Drink> listDrink;

    public CategoryAdapter(ArrayList<Category> listCategory){ this.listCategory = listCategory;}



    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.title.setText(listCategory.get(position).getCategoryName());
        listDrink = new ArrayList<Drink>(listCategory.get(position).getListDrink());
        drinkAdapter = new DrinkAdapter(listDrink);
        holder.rv_drink.setLayoutManager(new LinearLayoutManager(holder.rv_drink.getContext()));
        holder.rv_drink.setAdapter(drinkAdapter);
        Log.d("Log", "hehe");
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public RecyclerView rv_drink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            rv_drink = (RecyclerView) itemView.findViewById(R.id.rv_drink);
        }
    }
}
