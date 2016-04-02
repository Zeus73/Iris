package com.example.zeus.iris.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zeus.iris.Models.Movie;
import com.example.zeus.iris.Models.MoviesListResult;
import com.example.zeus.iris.MovieContract;
import com.example.zeus.iris.MovieListAdapter;
import com.example.zeus.iris.Networking.ApiClient;
import com.example.zeus.iris.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListByGenreActivity extends AppCompatActivity {
    private ListView movieListView;
    MovieListAdapter adapter;
    ProgressDialog progressDialog;
    ArrayList<Movie> movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_by_genre);
        progressDialog=new ProgressDialog(MovieListByGenreActivity.this);
        progressDialog.setTitle("Fetching Movies List");
        progressDialog.setMessage("In progress...");

        Intent i=getIntent();
        int genreId=i.getIntExtra("genreId", 18);
        Call<MoviesListResult>
                movieListByGenreActivityCall=
                ApiClient.getApiInterface().getMovieListByGenre(genreId, MovieContract.LoginContract.API_KEY);
        progressDialog.show();
        movieListByGenreActivityCall.enqueue(new Callback<MoviesListResult>() {
            @Override
            public void onResponse(Call<MoviesListResult> call, Response<MoviesListResult> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){

                    movieListView=(ListView) findViewById(R.id.movieListByGenreListview);
                    MoviesListResult hell=response.body();

                    movieList=hell.moviesArrayList;

                    adapter=new MovieListAdapter(MovieListByGenreActivity.this,movieList);
                    movieListView.setAdapter(adapter);


                    movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(MovieListByGenreActivity.this, "jjkxkxn", Toast.LENGTH_SHORT).show();
                            int movieId = movieList.get(position).movieId;
                            Log.i("raeched", String.valueOf(movieId));
                            Intent i = new Intent();
                            i.setClass(MovieListByGenreActivity.this, MovieDetailActivity.class);
                            i.putExtra("movieId", movieId);
                            startActivity(i);
                        }
                    });


                }else{
                    Toast.makeText(MovieListByGenreActivity.this, "Response unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MoviesListResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MovieListByGenreActivity.this, "call failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
