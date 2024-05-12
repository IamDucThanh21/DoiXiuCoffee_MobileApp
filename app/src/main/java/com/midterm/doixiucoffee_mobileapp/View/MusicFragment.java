package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midterm.doixiucoffee_mobileapp.Model.Song;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.SongAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentBookingBinding;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentMusicBinding;

import java.util.ArrayList;

public class MusicFragment extends Fragment {

    private ArrayList<Song> listSong;
    private FragmentMusicBinding binding;
    private SongAdapter songAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_music, null, false);

        binding.musicHb.homeBackImg.setImageResource(R.drawable.music_back);
        binding.musicHb.homeBackTitle.setVisibility(View.GONE);
        binding.musicHb.homeBackMusic.setVisibility(View.VISIBLE);

        binding.rvMusic.setLayoutManager(new LinearLayoutManager(this.getContext()));
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
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });

        View view = binding.getRoot();
        return view;
    }
}