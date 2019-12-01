package com.udacity.popularmoviesstage1app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage1app.R;
import com.udacity.popularmoviesstage1app.models.MovieList;

public class DetailActivity extends AppCompatActivity {

    TextView movieTitleTV, ratingTV, releaseDateTV, descriptionTV;
    ImageView posterIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        movieTitleTV = findViewById(R.id.movie_title_tv);
        ratingTV = findViewById(R.id.movie_rating_tv);
        releaseDateTV = findViewById(R.id.release_date_tv);
        descriptionTV = findViewById(R.id.movie_description_tv);
        posterIV = findViewById(R.id.movie_poster_iv);

        MovieList movie = intent.getParcelableExtra("movie");

        if (movie == null) {
            closeOnError();
        } else {
            movieTitleTV.setText(movie.title);
            Picasso.get()
                    .load(movie.posterPath)
                    .fit()
                    .error(R.mipmap.ic_launcher_round)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(posterIV);
            ratingTV.setText(String.valueOf(movie.voterAverage));
            releaseDateTV.setText(movie.releaseDate);
            descriptionTV.setText(movie.overview);
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
    }
}
