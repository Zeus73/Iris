package com.example.zeus.iris.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Zeus on 27-Mar-16.
 */
public class MoviesListResult {
   @SerializedName("results")
   public ArrayList<Movie> moviesArrayList;
}
