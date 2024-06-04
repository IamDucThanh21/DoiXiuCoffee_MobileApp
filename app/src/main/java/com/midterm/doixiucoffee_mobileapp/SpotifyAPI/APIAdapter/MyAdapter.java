package com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataSong;
import com.midterm.doixiucoffee_mobileapp.Model.Song;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.SpotifyAPI.Model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private final ArrayList<Item> items;

    public MyAdapter( ArrayList<Item> dataList) {
        this.items = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item, parent, false);
        return new MyViewHolder(itemView);
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
        holder.singer.setText(currentData.data.artists.items.get(0).profile.name);//currentData.data.artists.items.get(0).profile.name
        Picasso.get().load(currentData.data.albumOfTrack.coverArt.sources.get(0).url).into(holder.image);

        holder.btnAddMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song newSong = new Song();
                newSong.setIdSong(currentData.data.id);
                newSong.setName(currentData.data.name);
                newSong.setSinger(currentData.data.artists.items.get(0).profile.name);//currentData.data.artists.items.get(0).profile.name
                newSong.setVotes(0);
                newSong.setImage(currentData.data.albumOfTrack.coverArt.sources.get(0).url);

                DataSong.getInstance().addNewSong(newSong);
                Log.d("test", DataSong.getInstance().getListSong().size()+"");
                Navigation.findNavController(v).navigate(R.id.musicFragment);
            }
        });

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
        TextView singer;
        ImageView btnAddMusic;

        MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.musicImage);
            title = itemView.findViewById(R.id.musicTitle);
            btnAddMusic = (ImageView) itemView.findViewById(R.id.btn_add_music);
            singer = (TextView) itemView.findViewById(R.id.song_singer);
        }
    }
}
