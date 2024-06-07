package com.midterm.doixiucoffee_mobileapp.SpotifyAPI.APIAdapter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SpotifyAPIMusic {
    @Headers({"X-RapidAPI-Key: 3f60ddcd9fmsh4873837b22bdcfap18c1d6jsnf9acccf1bfed",
            "X-RapidAPI-Host: spotify23.p.rapidapi.com"})
    @GET("search")
    Call<MyData> getData(@Query("q") String query);

}
