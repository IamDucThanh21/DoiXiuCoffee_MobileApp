package com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private SpotifyAPIMusic api;
    private static  final String BASE_URL="https://spotify23.p.rapidapi.com/";

    public APIService() {
        api = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SpotifyAPIMusic.class);
    }
    public Call<MyData> getData(String nameSong){
        return api.getData(nameSong);
    }
}
