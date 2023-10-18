package com.isep.discoverprais.ui.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.isep.discoverprais.R;
import com.isep.discoverprais.models.Movie;
import android.content.Intent;

import java.util.List;

public class MovieAdapter extends BaseAdapter {
    private final Context context;
    private final List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false);
        }

        ImageView movieImageView = convertView.findViewById(R.id.movieImageView);
        TextView movieTitleTextView = convertView.findViewById(R.id.movieTitleTextView);

        Movie movie = movies.get(position);

        int imageResource = movie.getImgResource(context);
        movieImageView.setImageResource(imageResource);
        movieTitleTextView.setText(movie.getTitle());

        // Set a click listener for the movie item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event (open the movie detail)
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("movie_id", movie.getId()); // Pass the unique movie ID
                intent.putExtra("movie_title", movie.getTitle());
                intent.putExtra("movie_description", movie.getDescription());
                intent.putExtra("movie_video", movie.getVideo());
                context.startActivity(intent);
            }
        });

        return convertView;
    }


}
