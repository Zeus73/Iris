package com.example.zeus.iris;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tanvi on 26-03-2016.
 */
public interface ApiInterface {

    @GET("genre/movie/list"+Apikeyclass.API_KEY)
    Call<Genre> getGenre();




}
