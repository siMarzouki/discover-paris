package com.isep.discoverprais.ui.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import androidx.fragment.app.Fragment;
import com.isep.discoverprais.R;
import com.isep.discoverprais.services.MovieDataManager;
import com.isep.discoverprais.models.Movie;
import android.widget.ImageView;
import android.widget.TextView;

public class MoviesFragment extends Fragment {
    private MovieAdapter movieAdapter;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load movie data from resources when the fragment is created
        MovieDataManager.getInstance().loadMovieData(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        // Initialize the MovieAdapter with the list of movies
        movieAdapter = new MovieAdapter(requireContext(), MovieDataManager.getInstance().getMoviesList());

        // Set the adapter for the GridLayout
        GridLayout moviesGridLayout = view.findViewById(R.id.moviesGridLayout);

        for (int i = 0; i < movieAdapter.getCount(); i++) {
            // Get the movie at the current position
            Movie movie = (Movie) movieAdapter.getItem(i);

            // Inflate your movie item layout (the CardView) here
            View movieItem = getLayoutInflater().inflate(R.layout.list_item_movie, moviesGridLayout, false);

            // Set movie data (image and title) in the movie item (CardView)
            ImageView movieImageView = movieItem.findViewById(R.id.movieImageView);
            TextView movieTitleTextView = movieItem.findViewById(R.id.movieTitleTextView);

            int imageResource = movie.getImgResource(requireContext());
            movieImageView.setImageResource(imageResource);
            movieTitleTextView.setText(movie.getTitle());

            // Add the movie item to the GridLayout
            moviesGridLayout.addView(movieItem);

            // Set a click listener for the movie item
            movieItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the click event (open the movie detail)
                    Intent intent = new Intent(requireContext(), MovieDetailActivity.class);
                    intent.putExtra("movie_id", movie.getId());
                    intent.putExtra("movie_title", movie.getTitle());
                    intent.putExtra("movie_description", movie.getDescription());
                    intent.putExtra("movie_video", movie.getVideo());  // Pass the video file name
                    startActivity(intent);
                }
            });
        }

        return view;
    }
}
