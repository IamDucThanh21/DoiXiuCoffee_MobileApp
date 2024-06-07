package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.rpc.context.AttributeContext;
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

            //Kiểm tra xem nó đã được chọn sẵn chưa, nếu rồi thì tô màu
            for(Drink d: DataOrder.getInstance().getOrder().getListDrinks()){
                if(d.getIdDrink().equals(listDrink.get(position).getIdDrink())){
                    String size = d.getSizeInfo().getSize();
                    int s = 0;
                    if(size.equals("L")) s = 1;
                    setModeDrinkChoice(holder, s, "On");
                }
            }
            holder.priceS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.borderS.getVisibility() == View.VISIBLE) {
                        setModeDrinkChoice(holder, 0, "On");
                        setOrder(p, 0);
                    } else {
                        setModeDrinkChoice(holder, 0, "Off");
                        deleteDrinkOnOrder(p, 0);
                    }
                    Navigation.findNavController(v).navigate(R.id.addDrinkFragment);
                }
            });
            holder.priceB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.borderB.getVisibility()==View.VISIBLE){
                        setModeDrinkChoice(holder, 1, "On");
                        setOrder(p, 1);
                    }
                    else{
                        setModeDrinkChoice(holder, 1,"Off");
                        deleteDrinkOnOrder(p, 1);
                        Log.d("remove",DataOrder.getInstance().getOrder().getListDrinks().size()+"");
                    }
                    Navigation.findNavController(v).navigate(R.id.addDrinkFragment);
                }
            });

            //Nếu như đơn hiện tại đã đặt thì không thể thêm món mới
            if(DataOrder.getInstance().getOrder().getStatus().equals("waiting")){
                holder.priceS.setClickable(false);
                holder.priceB.setClickable(false);
            }
        }
    }
    public void setModeDrinkChoice(DrinkAdapter.ViewHolder holder, int size, String mode){
        Resources re =  holder.borderB.getResources();
        if(mode.equals("On")){
            if(size == 0){
                holder.borderS.setVisibility(View.GONE);
                holder.backS.setBackgroundColor(re.getColor(R.color.green));
                holder.priceS.setTextColor(re.getColor(R.color.white));
            }
            else{
                holder.borderB.setVisibility(View.GONE);
                holder.backB.setBackgroundColor(re.getColor(R.color.green));
                holder.priceB.setTextColor(re.getColor(R.color.white));
            }
        }
        else{
            if(size == 0){
                holder.borderS.setVisibility(View.VISIBLE);
                holder.backS.setBackgroundColor(re.getColor(R.color.white));
                holder.priceS.setTextColor(re.getColor(R.color.gray_text));
            }
            else {
                holder.borderB.setVisibility(View.VISIBLE);
                holder.backB.setBackgroundColor(re.getColor(R.color.white));
                holder.priceB.setTextColor(re.getColor(R.color.gray_text));
            }
        }
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
