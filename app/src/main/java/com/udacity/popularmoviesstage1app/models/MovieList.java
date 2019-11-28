package com.udacity.popularmoviesstage1app.models;

public class MovieList {

    public String id;
    public String title;
    public String posterPath;


    public MovieList(String id, String title, String posterPath) {

        final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";
        this.id = id;
        this.title = title;
        this.posterPath = POSTER_BASE_URL + posterPath;

    }
}
