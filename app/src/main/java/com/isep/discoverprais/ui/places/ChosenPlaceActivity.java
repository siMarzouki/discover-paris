package com.isep.discoverprais.ui.places;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.isep.discoverprais.R;
import com.isep.discoverprais.models.Place;
import com.isep.discoverprais.services.DataManager;

import java.util.List;

public class ChosenPlaceActivity extends AppCompatActivity {

    private void openGoogleMapsDirections(double destinationLatitude,double destinationLongitude) {


        // Create a URI for the Google Maps directions
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + destinationLatitude + "," + destinationLongitude);

        // Create an Intent to open Google Maps with directions
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps"); // Specify the Google Maps package

        // Check if Google Maps is installed
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // If Google Maps is not installed, you can provide an alternative action, like opening a web browser with Google Maps
            Uri webUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" + destinationLatitude + "," + destinationLongitude);
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
            startActivity(webIntent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_place);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
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


                Button btn =findViewById(R.id.placeButton);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openGoogleMapsDirections(chosenPlace.getLatitude(),chosenPlace.getLongitude());
                    }
                });

            }
        }



    }
}