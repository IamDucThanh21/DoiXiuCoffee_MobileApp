package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    private ArrayList<Drink> listOrderDrink;
    private int mode =0;

    public OrderAdapter(ArrayList<Drink> listOrderDrink,int mode) {
        this.listOrderDrink = listOrderDrink;
        this.mode = mode;
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
        holder.drinkName.setText(listOrderDrink.get(position).getDrinkName());
        holder.price.setText(listOrderDrink.get(position).getSizeInfo().getPrice()+"K");

        //Chế độ mode = 1 là của admin
        int i = position;
        if(mode==1){
            holder.btnRemove.setVisibility(View.GONE);
            holder.btnTakeNote.setVisibility(View.GONE);
        }

        //sự kiện cho nút xóa, xóa xong thì reset lại
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

        //Sự kiện take note
        holder.btnTakeNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.note.setVisibility(View.VISIBLE);
            }
        });

        //Lưu lại note
        holder.etNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(DataOrder.getInstance().getOrder().getListDrinks().size()!=0){
                    DataOrder.getInstance().getOrder().getListDrinks().get(i).setNote(s.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Nếu không phải ở trạng thái booking thì ko đươc sửa
        if(!DataOrder.getInstance().getOrder().getStatus().equals("booking")){
            holder.btnRemove.setClickable(false);
        }

        //Kiểm tra xem, nếu có note thì hiện
        if(!listOrderDrink.get(i).getNote().trim().equals("")){
            holder.note.setVisibility(View.VISIBLE);
            holder.etNote.setText(listOrderDrink.get(i).getNote());
        }
        else holder.note.setVisibility(View.GONE);
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
        private LinearLayout note;
        private EditText etNote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            drinkName = (TextView) itemView.findViewById(R.id.drinkName);
            price = (TextView)  itemView.findViewById(R.id.price);
            btnRemove = (ImageView) itemView.findViewById(R.id.btn_remove);
            btnTakeNote = (ImageView) itemView.findViewById(R.id.btn_take_note);
            note = (LinearLayout) itemView.findViewById(R.id.note);
            etNote = (EditText) itemView.findViewById(R.id.et_note);
        }
    }
}
