package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.doixiucoffee_mobileapp.Model.Feedback;
import com.midterm.doixiucoffee_mobileapp.R;

import java.util.ArrayList;

public class FeebackAdapter extends RecyclerView.Adapter<FeebackAdapter.ViewHolder> {
    private ArrayList<Feedback> listFeedback;

    public FeebackAdapter (ArrayList<Feedback> listFeedback){this.listFeedback = listFeedback;}

    @NonNull
    @Override
    public FeebackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feedback, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeebackAdapter.ViewHolder holder, int position) {
        holder.useName.setText(listFeedback.get(position).getUser().getName());
        holder.time.setText(listFeedback.get(position).getDate().toString());
        holder.content.setText(listFeedback.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return listFeedback.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView useName;
        private TextView time;
        private TextView content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            useName = (TextView) itemView.findViewById(R.id.user_name);
            time = (TextView) itemView.findViewById(R.id.time);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
