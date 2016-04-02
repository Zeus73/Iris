package com.example.zeus.iris.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zeus.iris.Models.Movie;
import com.example.zeus.iris.Models.MoviesListResult;
import com.example.zeus.iris.MovieContract;
import com.example.zeus.iris.Networking.ApiClient;
import com.example.zeus.iris.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSearchResultActivity extends AppCompatActivity {

    private ListView movieListView;
    ArrayAdapter<String> adapter;
    ProgressDialog progressDialog;
    ArrayList<Movie> movieList;
    ArrayList<String> movieNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search_result);
        progressDialog=new ProgressDialog(MovieSearchResultActivity.this);
        progressDialog.setTitle("Fetching Search Results");
        progressDialog.setMessage("In progress...");

        Intent i=getIntent();
        String findMovieKeyword=i.getStringExtra("findMovieKeyword");
        Call<MoviesListResult>
                movieListByKeywordActivityCall=
                ApiClient.getApiInterface().getMoviesListByKeyword(MovieContract.LoginContract.API_KEY,findMovieKeyword);
        progressDialog.show();
        movieListByKeywordActivityCall.enqueue(new Callback<MoviesListResult>() {
            @Override
            public void onResponse(Call<MoviesListResult> call, Response<MoviesListResult> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){

                    movieListView=(ListView) findViewById(R.id.movieSearchResultList);
                    MoviesListResult hell=response.body();
                    movieList=hell.moviesArrayList;
                    movieNames=new ArrayList<String>();
                    for(int i=0;i<movieList.size();++i)
                        movieNames.add(movieList.get(i).movieName);
//                    Log.i("Check",movieList.get(1).movieName);
                    adapter=new ArrayAdapter<String>(MovieSearchResultActivity.this,android.R.layout.simple_list_item_1,movieNames);
                    movieListView.setAdapter(adapter);

                    movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            int movieId=movieList.get(position).movieId;
                            Intent i=new Intent();
                            i.setClass(MovieSearchResultActivity.this,MovieDetailActivity.class);
                            i.putExtra("movieId", movieId);
                            startActivity(i);
                        }
                    });


                }else{
                    Toast.makeText(MovieSearchResultActivity.this, "Response unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MoviesListResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MovieSearchResultActivity.this, "call failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
