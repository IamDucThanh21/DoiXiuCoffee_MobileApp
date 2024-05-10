package com.midterm.doixiucoffee_mobileapp.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.doixiucoffee_mobileapp.MainActivity;
import com.midterm.doixiucoffee_mobileapp.Model.Song;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.View.Adapter.SongAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.MusicLayoutBinding;

import java.util.ArrayList;

public class MusicLayout extends AppCompatActivity {
    private MusicLayoutBinding binding;
    private ArrayList<Song> listSong;
    private SongAdapter songAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MusicLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.musicHb.homeBackImg.setImageResource(R.drawable.music_back);
        binding.musicHb.homeBackTitle.setVisibility(View.GONE);
        binding.musicHb.homeBackMusic.setVisibility(View.VISIBLE);

        binding.rvMusic.setLayoutManager(new LinearLayoutManager(this));
        listSong = new ArrayList<>();
        songAdapter = new SongAdapter(listSong);
        binding.rvMusic.setAdapter(songAdapter);


        listSong.add(new Song("Đại lộ mặt trời", "Chillies", 4));
        listSong.add(new Song("Soạn", "The Cassette", 0));

        //Lấy thằng đầu danh sách làm bìa, từ thằng thứ 2 trở đi vào recyclview
        Song firstSong = listSong.get(0);
        listSong.remove(0);
        binding.musicHb.musicName.setText(firstSong.getName());
        binding.musicHb.musicSinger.setText(firstSong.getSinger());

        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicLayout.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
