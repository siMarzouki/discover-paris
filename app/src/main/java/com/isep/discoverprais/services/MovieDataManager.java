package com.isep.discoverprais.services;

import android.content.Context;
import com.isep.discoverprais.R;
import com.isep.discoverprais.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MovieDataManager {
    private static MovieDataManager instance;
    private List<Movie> movies;
    private int nextMovieId = 1; // A variable to generate unique IDs for movies

    private MovieDataManager() {
        movies = new ArrayList<>();
    }

    public static MovieDataManager getInstance() {
        if (instance == null) {
            instance = new MovieDataManager();
        }
        return instance;
    }

    public void loadMovieData(Context context) {
        movies.clear();
        nextMovieId = 1; // Reset the movie ID counter

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.movie_data);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                String img = jsonObject.getString("img");
                String video = jsonObject.optString("video", ""); // Get the video file name, default to empty string if not provided
                Movie movie = new Movie(nextMovieId, title, description, img, video); // Include the unique ID
                movies.add(movie);
                nextMovieId++; // Increment the movie ID counter
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getMoviesList() {
        return movies;
    }
}
