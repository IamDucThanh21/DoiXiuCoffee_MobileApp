package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.rpc.context.AttributeContext;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataDrink;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.Order;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.View.ListOdersFragment;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentListOdersBinding;

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

        int p = position;
        if(mode == 1){
            //Thêm viền cho chế độ order
            holder.borderB.setVisibility(View.VISIBLE);
            holder.borderS.setVisibility(View.VISIBLE);

            holder.priceS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Resources re =  holder.borderB.getResources();
                    holder.borderS.setVisibility(View.GONE);
                    holder.backS.setBackgroundColor(re.getColor(R.color.green));
                    holder.priceS.setTextColor(re.getColor(R.color.white));
                    setOrder(p, 0);
                    Navigation.findNavController(v).navigate(R.id.addDrinkFragment);
                }
            });
            holder.priceB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Resources re =  holder.borderB.getResources();
                    holder.borderB.setVisibility(View.GONE);
                    holder.backB.setBackgroundColor(re.getColor(R.color.green));
                    holder.priceB.setTextColor(re.getColor(R.color.white));
                    setOrder(p, 1);
                    Navigation.findNavController(v).navigate(R.id.addDrinkFragment);
                }
            });

            //Nếu như đơn hiện tại đã đặt thì không thể thêm món mới
            if(DataOrder.getInstance().getOrder().getStatus().equals("waiting")){
                holder.priceS.setClickable(false);
                holder.priceB.setClickable(false);
            }
        }

        holder.moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tvInfo.getVisibility() == View.GONE){
                    holder.tvInfo.setVisibility(View.VISIBLE);
                    holder.tvInfo.setText(listDrink.get(p).getStory());
                }
                else holder.tvInfo.setVisibility(View.GONE);
            }
        });
    }

    public void setOrder(int p, int size){
        Drink drink = new Drink(listDrink.get(p).getIdDrink(), size);
        DataOrder.getInstance().getOrder().getListDrinks().add(drink);
        DataOrder.getInstance().updateTotalPrice();
        Log.d("action","Add drink:"+ drink.getIdDrink()+"/"+drink.getSizeInfo().getSize());

    }
    public void deleteDrinkOnOrder(int p, int size){
        Drink drink = new Drink(listDrink.get(p).getIdDrink(), size);
        DataOrder.getInstance().getOrder().removeDrink(drink.getIdDrink(), size);
        DataOrder.getInstance().updateTotalPrice();
        Log.d("action","Remove drink:"+ drink.getIdDrink()+"/"+drink.getSizeInfo().getSize());

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
        public ImageView moreInfo;
        public TextView tvInfo;
        public ViewHolder(View view){
            super(view);
            drinkName = (TextView) view.findViewById(R.id.drinkName);
            priceS = (TextView) view.findViewById(R.id.priceS);
            priceB = (TextView) view.findViewById(R.id.priceB);
            borderS = (ShapeableImageView) view.findViewById(R.id.borderS);
            borderB = (ShapeableImageView) view.findViewById(R.id.borderB);
            backS = (ShapeableImageView) view.findViewById(R.id.backS);
            backB = (ShapeableImageView) view.findViewById(R.id.backB);
            moreInfo = (ImageView) view.findViewById(R.id.more_info);
            tvInfo = (TextView) view.findViewById(R.id.tv_infor);
        }

    }
}
