package com.example.zeus.iris.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zeus.iris.Genre;
import com.example.zeus.iris.GenreArrayAdapter;
import com.example.zeus.iris.GenreDetail;
import com.example.zeus.iris.Networking.ApiClient;
import com.example.zeus.iris.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreActivity extends AppCompatActivity {



   ArrayList<GenreDetail> gd;

  GenreDetail detail;
    GenreArrayAdapter adapter;

    ListView lv;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);


        gd=new ArrayList<GenreDetail>();

   lv=(ListView)findViewById(R.id.genreListview);


        Call<Genre> call = ApiClient.getApiInterface().getGenre();
        progressDialog=new ProgressDialog(GenreActivity.this);
        progressDialog.setTitle("Fetching genres");
        progressDialog.setMessage("Wait");
        progressDialog.show();


        call.enqueue(new Callback<Genre>() {
            @Override
            public void onResponse(Call<Genre> call, Response<Genre> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Genre genre = response.body();
                    gd = genre.genres;


                    adapter=new GenreArrayAdapter(GenreActivity.this,gd);
                    lv.setAdapter(adapter);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                        {           adapter=(GenreArrayAdapter)parent.getAdapter();

                                 detail=adapter.getItem(position);
                            Toast.makeText(GenreActivity.this,detail.getGenre_name(),Toast.LENGTH_LONG).show();
                            Intent i=new Intent();
                            i.setClass(GenreActivity.this,MovieListByGenreActivity.class);
                            i.putExtra("genreId",detail.getGenre_id());
                            startActivity(i);


                        }
                    });



                } else
                    Toast.makeText(GenreActivity.this, response.message() + response.code(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<Genre> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GenreActivity.this, "On Failure", Toast.LENGTH_LONG).show();

            }



        });









    }
}
