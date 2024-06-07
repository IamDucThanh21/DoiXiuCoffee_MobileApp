package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.Song;
import com.midterm.doixiucoffee_mobileapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private ArrayList<Song> listSong;

    public SongAdapter(ArrayList<Song> listSong){
        listSong.remove(0);
        this.listSong = listSong;
    }


    @NonNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.ViewHolder holder, int position) {
        holder.songName.setText(listSong.get(position).getName());
        holder.songSinger.setText(listSong.get(position).getSinger());
        holder.songVotes.setText(listSong.get(position).getVotes()+"");
        Picasso.get().load(listSong.get(position).getImage()).into(holder.avaSong);
        if(DataPerson.getInstance().isAdminById(DataPerson.getInstance().getIdPersonLogin())){
            holder.layoutPersonHearts.setVisibility(View.GONE);
            holder.layoutPersonChooses.setVisibility(View.GONE);
            holder.layoutGarbage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView songName;
        public TextView songSinger;
        public TextView songVotes;
        public ShapeableImageView avaSong;
        public LinearLayout layoutPersonChooses;
        public LinearLayout layoutPersonHearts;
        public LinearLayout layoutGarbage;
//        public TextView numPersonVotes;
//        public ImageView personVotes;
//        public ImageView imvHeart;
//        public ImageView imvGarbage;
        public ViewHolder(View itemView) {
            super(itemView);
            songName = (TextView) itemView.findViewById(R.id.song_name);
            songSinger = (TextView) itemView.findViewById(R.id.song_singer);
            songVotes = (TextView) itemView.findViewById(R.id.song_votes);
            avaSong = (ShapeableImageView) itemView.findViewById(R.id.song_image);
//            numPersonVotes = (TextView) itemView.findViewById(R.id.song_votes);
//            personVotes = (ImageView) itemView.findViewById(R.id.)
            layoutPersonChooses = (LinearLayout) itemView.findViewById(R.id.person_chooses);
            layoutPersonHearts = (LinearLayout) itemView.findViewById(R.id.heart_votes);
            layoutGarbage = (LinearLayout) itemView.findViewById(R.id.garbage);
        }
    }
}
