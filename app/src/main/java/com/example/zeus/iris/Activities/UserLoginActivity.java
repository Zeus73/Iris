package com.example.zeus.iris.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zeus.iris.R;

import java.util.ArrayList;

public class UserLoginActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> userLoginOptionsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        userLoginOptionsArrayList =new ArrayList<>();

        userLoginOptionsArrayList.add("1.Search For A movie.");
        userLoginOptionsArrayList.add("2.Discover Movies By Genre.");
        userLoginOptionsArrayList.add("3.Get Favourites List.");
        userLoginOptionsArrayList.add("4.Get Movie watch List.");
        userLoginOptionsArrayList.add("5.Rate a Movie.");
        userLoginOptionsArrayList.add("6.Get List of rated movies.");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,userLoginOptionsArrayList);
        ListView lv=(ListView) findViewById(R.id.userLoginListView);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    AlertDialog.Builder bd=new AlertDialog.Builder(UserLoginActivity.this);
                    bd.setTitle("Search Movie-");
                    bd.setMessage("Enter Name of Movie:");
                    View v=getLayoutInflater().inflate(R.layout.search_movie_dialog,null);
                    bd.setView(v);
                    final EditText et=(EditText) v.findViewById(R.id.searchMovieEditText);
                    bd.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String findMovieKeyword;
                            if(et!=null) {
                                Intent i= new Intent();
                                findMovieKeyword = et.getText().toString();
                                i.setClass(UserLoginActivity.this,MovieSearchResultActivity.class);
                                i.putExtra("findMovieKeyword",findMovieKeyword);
                                startActivity(i);
                            }
                        }
                    });
                    bd.create().show();
                }else if(position==1){
                    Intent i= new Intent();
                    i.setClass(UserLoginActivity.this, GenreActivity.class);
                    startActivity(i);
                }else if(position==2){

                }else if(position==3){

                }else if(position==4){

                }else if(position==5){

                }


            }
        });

    }
}
