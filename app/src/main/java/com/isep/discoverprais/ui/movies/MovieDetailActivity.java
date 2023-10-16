package com.isep.discoverprais.ui.movies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.isep.discoverprais.R;
import android.widget.ImageView;
import android.view.View;

public class MovieDetailActivity extends AppCompatActivity {

    private VideoView videoView;
    private ImageView replayButton;
    private Button visitedButton;
    private Button resetButton;

    private SharedPreferences sharedPreferences;
    private boolean isVisited;
    private int movieId; // Unique identifier for the movie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Get the unique movie identifier from the intent (e.g., movie_id)
        movieId = getIntent().getIntExtra("movie_id", 0);
        sharedPreferences = getSharedPreferences("MoviePrefs_" + movieId, MODE_PRIVATE);

        // Initialize SharedPreferences with a unique key for each movie
        isVisited = sharedPreferences.getBoolean("isVisited", false);

        // Get references to UI elements
        TextView titleTextView = findViewById(R.id.movieDetailTitle);
        TextView descriptionTextView = findViewById(R.id.movieDetailDescription);
        videoView = findViewById(R.id.movieVideoView);
        replayButton = findViewById(R.id.replayButton);

        visitedButton = findViewById(R.id.visitedButton);
        resetButton = findViewById(R.id.resetButton);

        // Get movie title, description, and video file from extras
        String title = getIntent().getStringExtra("movie_title");
        String description = getIntent().getStringExtra("movie_description");
        String videoFile = getIntent().getStringExtra("movie_video");

        // Set the title and description in the TextViews
        titleTextView.setText(title);
        descriptionTextView.setText(description);

        // Set the video from the resource
        int videoResId = getResourceIdForVideo(videoFile);
        String videoPath = "android.resource://" + getPackageName() + "/" + videoResId;
        videoView.setVideoPath(videoPath);
        videoView.start();

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the back button click (e.g., finish the activity to go back)
                finish();
            }
        });

        // Set a click listener for the replay button
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Restart the video playback from the beginning
                videoView.seekTo(0);
                videoView.start();
            }
        });

        // Set the initial color of the "Visited" button based on the stored state
        int initialButtonColor = isVisited ? getResources().getColor(R.color.button_visited) : getResources().getColor(R.color.button_normal);
        visitedButton.setBackgroundColor(initialButtonColor);

        // Set a click listener for the "Visited" button
        visitedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isVisited = !isVisited; // Toggle the button state

                // Change the button color based on the state
                int buttonColor = isVisited ? getResources().getColor(R.color.button_visited) : getResources().getColor(R.color.button_normal);
                visitedButton.setBackgroundColor(buttonColor);

                // Save the button state to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isVisited", isVisited);
                editor.apply();
            }
        });

        // Set a click listener for the "Reset" button
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset the button color to the default state (button_normal)
                visitedButton.setBackgroundColor(getResources().getColor(R.color.button_normal));

                // Update the button state and save to SharedPreferences
                isVisited = false;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isVisited", isVisited);
                editor.apply();
            }
        });
    }

    private int getResourceIdForVideo(String videoName) {
        // Get the resource ID for the video based on the name provided in JSON
        return getResources().getIdentifier(videoName, "raw", getPackageName());
    }
}
