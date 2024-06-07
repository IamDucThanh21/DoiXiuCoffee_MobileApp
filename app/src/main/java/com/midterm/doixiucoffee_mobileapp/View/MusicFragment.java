package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataSong;
import com.midterm.doixiucoffee_mobileapp.Model.Song;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.SongAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentMusicBinding;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
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

        String idPersonLogin = DataPerson.getInstance().getIdPersonLogin();
        if (!idPersonLogin.equals("null")){
            binding.toolbar.imgAva.setVisibility(View.VISIBLE);
            String img = DataPerson.getInstance().getPersonById(idPersonLogin).getImage();
            binding.toolbar.imgAva.setImageBitmap(DataPerson.getInstance().base64toBitmap(img));
            binding.toolbar.btnLogin.setVisibility(View.GONE);
        }

        binding.musicHb.homeBackImg.setImageResource(R.drawable.music_back);
        binding.musicHb.homeBackTitle.setVisibility(View.GONE);
        binding.musicHb.homeBackMusic.setVisibility(View.VISIBLE);

        binding.rvMusic.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listSong = new ArrayList<>();
        listSong = DataSong.getInstance().getListSong();

        DataSong.getInstance().sortSong();

        if(listSong.size()!=0){
            //Lấy thằng đầu danh sách làm bìa, từ thằng thứ 2 trở đi vào recyclview
            Song firstSong = listSong.get(0);
            binding.musicHb.musicName.setText(firstSong.getName());
            binding.musicHb.musicSinger.setText(firstSong.getSinger());
            Picasso.get().load(firstSong.getImage()).into(binding.musicHb.musicImg);


            songAdapter = new SongAdapter(listSong);
            binding.rvMusic.setAdapter(songAdapter);
        }
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });

        binding.btnAddMusic.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(v).navigate(R.id.searchMusicFragment);

                SearchMusicFragment searchMusicFragment = SearchMusicFragment.newInstance();

                binding.frameLayout.setVisibility(View.VISIBLE);
                binding.btnAddMusic.addddd.setVisibility(View.GONE);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.frameLayout, searchMusicFragment)
                        .commit();
            }
        });

        //Xem thoong tin cá nhân khi nhấn vào ava
        binding.toolbar.imgAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.infoUserFragment);
            }
        });

        View view = binding.getRoot();
        return view;
    }
}