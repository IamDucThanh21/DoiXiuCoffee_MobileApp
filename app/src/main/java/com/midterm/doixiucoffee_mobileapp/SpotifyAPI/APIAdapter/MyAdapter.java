package com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.midterm.doixiucoffee_mobileapp.SpotifyAPI.Model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private final Activity context;
    private final ArrayList<Item> items;

    public MyAdapter(Activity context, ArrayList<Item> dataList) {
        this.context = context;
        this.items = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false);
        //return new MyViewHolder(itemView);
        return null;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item currentData = items.get(position);
        // MediaPlayer mediaPlayer = MediaPlayer.create(context, Uri.parse("https://open.spotify.com/track/6M3x8hn7OYct4DXZZYKvEa"));//currentData.data.uri
        holder.title.setText(currentData.data.name);
        Picasso.get().load(currentData.data.albumOfTrack.coverArt.sources.get(0).url).into(holder.image);

//        holder.play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.start();
//            }
//        });
//        holder.pause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//            }
//        });
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        ImageButton play;
        ImageButton pause;

        MyViewHolder(View itemView) {
            super(itemView);
//            image = itemView.findViewById(R.id.musicImage);
//            title = itemView.findViewById(R.id.musicTitle);
//            play = itemView.findViewById(R.id.btnPlay);
//            pause = itemView.findViewById(R.id.btnPause);
        }
    }
}
