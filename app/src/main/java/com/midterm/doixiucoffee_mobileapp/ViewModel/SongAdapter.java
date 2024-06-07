package com.midterm.doixiucoffee_mobileapp.ViewModel;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataSong;
import com.midterm.doixiucoffee_mobileapp.Model.Song;
import com.midterm.doixiucoffee_mobileapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private ArrayList<Song> listSong;
    private boolean checkOnScrenn = false;
    public SongAdapter(ArrayList<Song> listSong){
        listSong.remove(0);
//        if(!checkOnScrenn){
//            listSong.remove(0);
//            checkOnScrenn = true;
//        }
        this.listSong = listSong;
    }


    @NonNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_music, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
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
        holder.imvGarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set khi nhấn vào cái thùng rác thì nó in đậm
                holder.imvGarbage.setImageResource(R.drawable.garbage_after);
                // xóa nhạc trong database
                DataSong.getInstance().deleteMusicById(listSong.get(position).getIdSong());
                // xóa nhạc trong this listsong
                DataSong.getInstance().getListSong().remove(listSong.get(position));
                // xóa nhạc trong song chương trình
                Navigation.findNavController(v).navigate(R.id.musicFragment);
            }
        });
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
        public ImageView imvGarbage;
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
            imvGarbage = (ImageView) itemView.findViewById(R.id.imv_garbage);
        }
    }
}
