package com.isep.discoverprais.ui.places;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.isep.discoverprais.R;
import com.isep.discoverprais.models.Place;
import com.isep.discoverprais.services.DataManager;

import java.util.List;

public class ChosenPlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_place);

        Intent intent = getIntent();
        if (intent != null) {
            getSupportActionBar().setTitle("Place");

            int position = intent.getIntExtra("position", -1);

            if (position != -1) {
                List<Place> places = DataManager.getInstance().getPlacesList();
                Place chosenPlace = places.get(position);

                int image = getResources().getIdentifier(chosenPlace.getImg(), "raw", getPackageName());
                ImageView placeImageView = findViewById(R.id.placePhoto);
                placeImageView.setImageResource(image);

                TextView placeTitleTextView = findViewById(R.id.placeName);
                TextView placeDescriptionTextView = findViewById(R.id.placeDescription);

                placeTitleTextView.setText(chosenPlace.getName());
                placeDescriptionTextView.setText(chosenPlace.getDescription());
            }
        }
    }
}