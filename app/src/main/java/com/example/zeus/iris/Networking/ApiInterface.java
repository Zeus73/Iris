package com.example.zeus.iris.Networking;

import com.example.zeus.iris.Apikeyclass;
import com.example.zeus.iris.Genre;
import com.example.zeus.iris.Models.MovieDetail;
import com.example.zeus.iris.Models.MoviesListResult;
import com.example.zeus.iris.Models.RawRequestToken;
import com.example.zeus.iris.Models.SessionIdClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Zeus on 26-Mar-16.
 */
public interface ApiInterface {

    @GET("genre/movie/list"+ Apikeyclass.API_KEY)
    Call<Genre> getGenre();     //Tanvi's method

    @GET("authentication/token/new")
    Call<RawRequestToken> getRawRequestToken(@Query("api_key") String API_KEY);

    @GET("authentication/token/validate_with_login")
    Call<RawRequestToken> validateRequestToken(@Query("api_key") String API_KEY,
                                               @Query("request_token") String REQUEST_TOKEN,
                                               @Query("username") String username,
                                               @Query("password") String password);
    @GET("authentication/session/new")
    Call<SessionIdClass> getSessionId(@Query("api_key") String API_KEY,
                                              @Query("request_token") String REQUEST_TOKEN);

    @GET("genre/{id}/movies")
    Call<MoviesListResult> getMovieListByGenre(@Path("id") int genreId,@Query("api_key") String API_KEY);

    @GET("search/movie")
    Call<MoviesListResult> getMoviesListByKeyword(@Query("api_key") String API_Key,@Query("query") String keyword);

    @GET("movie/{id}")
    Call<MovieDetail> getMovieDetail(@Path("id")int id,@Query("api_key") String API_Key);



}
