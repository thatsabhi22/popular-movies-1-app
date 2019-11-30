package com.udacity.popularmoviesstage1app.ui;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.popularmoviesstage1app.R;
import com.udacity.popularmoviesstage1app.adapters.MoviesGridAdpater;
import com.udacity.popularmoviesstage1app.models.MovieList;
import com.udacity.popularmoviesstage1app.tasks.MovieLoader;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MovieList>> {

    public static final String LOG_TAG = MainActivity.class.getName();

    /**
     * URL for movies data from the MoviesDB dataset
     */
    private static final String MOVIES_REQUEST_URL =
            "http://api.themoviedb.org/3/movie/popular?api_key=5dae56b7517d66c0d3da2e78ad58bc23";

    /**
     * Constant value for the movie loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int MOVIE_LOADER_ID = 1;

    private static final int NUM_OF_COLUMNS = 2;

    private RecyclerView moviesGridRecyclerView;
    private MoviesGridAdpater moviesGridAdpater;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesGridRecyclerView = findViewById(R.id.MoviesGridRecyclerView);

        moviesGridAdpater = new MoviesGridAdpater(this, new ArrayList<MovieList>());

        moviesGridRecyclerView.setAdapter(moviesGridAdpater);

        mLayoutManager = new GridLayoutManager(this, NUM_OF_COLUMNS);

        moviesGridRecyclerView.setLayoutManager(mLayoutManager);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(MOVIE_LOADER_ID, null, this);

    }

    @Override
    public Loader<List<MovieList>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new MovieLoader(this, MOVIES_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<MovieList>> loader, List<MovieList> movies) {
        moviesGridAdpater.setData(movies);
    }

    @Override
    public void onLoaderReset(Loader<List<MovieList>> loader) {
        // Loader reset, so we can clear out our existing data.
        moviesGridAdpater.setData(new ArrayList<MovieList>());
    }
}