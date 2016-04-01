package com.example.zeus.iris.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zeus.iris.Models.MovieDetail;
import com.example.zeus.iris.MovieContract;
import com.example.zeus.iris.Networking.ApiClient;
import com.example.zeus.iris.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    TextView movieName;
    TextView moviePopularity;
    TextView movieOverview;
    ImageView moviePoster;
    TextView movieLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent i=getIntent();
        int movieId=i.getIntExtra("movieId", 555);
        Call<MovieDetail>MovieDetailCall=
                ApiClient.getApiInterface()
                        .getMovieDetail(movieId, MovieContract.LoginContract.API_KEY);
        MovieDetailCall.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if(response.isSuccessful()){
                    MovieDetail ans=response.body();
                    movieName=(TextView)findViewById(R.id.movieDetailTitle);
                    moviePopularity=(TextView)findViewById(R.id.movieDetailPopularity);
                    movieOverview=(TextView)findViewById(R.id.movieDetailOverview);
                    moviePoster=(ImageView)findViewById(R.id.movieDetailPoster);
                    movieLanguage=(TextView)findViewById(R.id.movieDetailLanguage);

                    movieName.setText(ans.movieName);
                    moviePopularity.setText(String.valueOf(ans.moviePopularity));
                    movieLanguage.setText(ans.language);
                    movieOverview.setText(ans.overview);
//                    Picasso.with(MovieDetailActivity.this).load(ans.posterLink).into(moviePoster);
                    Picasso.with(MovieDetailActivity.this).load(Uri.parse("https://image.tmdb.org/t/p/w185" + ans.posterLink)).into(moviePoster);
                }else{
                    Toast.makeText(MovieDetailActivity.this, "Movie Detail Response unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this, "Response Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
