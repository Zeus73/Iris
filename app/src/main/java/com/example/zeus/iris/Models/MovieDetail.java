package com.example.zeus.iris.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zeus on 02-Apr-16.
 */
public class MovieDetail {
    @SerializedName("id")
    public int movieId;
    @SerializedName("original_title")
    public String movieName;
    @SerializedName("popularity")
    public double moviePopularity;

    public String overview;
    @SerializedName("poster_path")
    public String posterLink;
    @SerializedName("original_language")
    public String language;
}
