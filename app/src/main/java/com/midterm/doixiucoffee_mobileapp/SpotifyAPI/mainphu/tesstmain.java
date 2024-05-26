package com.midterm.doixiucoffee_mobileapp.SpotifyAPI.mainphu;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter.APIService;
import com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter.MyAdapter;
import com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter.MyData;
import com.midterm.doixiucoffee_mobileapp.SpotifyAPI.Model.Tracks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tesstmain extends AppCompatActivity {
    private APIService apiService;

    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        recyclerView = findViewById(R.id.rcv_test);

        apiService = new APIService();
        String nameSong = "";
        apiService.getData(nameSong).enqueue(new Callback<MyData>() {
            @Override
            public void onResponse(Call<MyData> call, Response<MyData> response) {
                Tracks tracks = response.body().tracks;
                // Uncomment the following lines if needed:
//                 TextView txt_test = findViewById(R.id.txt_test_api);
//                 txt_test.setText(tracks.toString());
                 myAdapter = new MyAdapter(tesstmain.this, tracks.items);
                 recyclerView.setAdapter(myAdapter);
                 recyclerView.setLayoutManager(new LinearLayoutManager(tesstmain.this));

                Log.d("TAG: onResponse", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<MyData> call, Throwable throwable) {
                Log.d("TAG: onFailure", "onFailure: " + throwable.getMessage());
            }
        });


    }
}