package com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SpotifyAPIMusic {
    @Headers({"X-RapidAPI-Key: 8fa1abe269mshabf1b79b5e22ae0p112547jsn39f56d60df13",
            "X-RapidAPI-Host: spotify23.p.rapidapi.com"})
    @GET("search")
    Call<MyData> getData(@Query("q") String query);

}
