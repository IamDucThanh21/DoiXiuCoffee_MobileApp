package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter.APIService;
import com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter.MyAdapter;
import com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter.MyData;
import com.midterm.doixiucoffee_mobileapp.SpotifyAPI.Model.Tracks;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentSearchMusicBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMusicFragment extends Fragment {
    private APIService apiService;

    private MyAdapter myAdapter;

    FragmentSearchMusicBinding binding;

    public SearchMusicFragment(){};

    public static SearchMusicFragment newInstance(){
        SearchMusicFragment fragment = new SearchMusicFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_search_music, null, false);

        binding.etSearch.requestFocus();

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                apiService = new APIService();
                String nameSong = s.toString();
                apiService.getData(nameSong).enqueue(new Callback<MyData>() {
                    @Override
                    public void onResponse(Call<MyData> call, Response<MyData> response) {
                        Tracks tracks = response.body().tracks;
                        myAdapter = new MyAdapter(tracks.items);
                        binding.rcvTest.setAdapter(myAdapter);
                        binding.rcvTest.setLayoutManager(new LinearLayoutManager(getContext()));

                        Log.d("TAG: onResponse", "onResponse: " + response.body());
                    }

                    @Override
                    public void onFailure(Call<MyData> call, Throwable throwable) {
                        Log.d("TAG: onFailure", "onFailure: " + throwable.getMessage());
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        View view = binding.getRoot();
        return view;
    }
}