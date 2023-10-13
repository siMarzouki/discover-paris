package com.isep.discoverprais.ui.places;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.isep.discoverprais.R;
import com.isep.discoverprais.models.Place;

import java.util.List;

public class PlaceAdapter extends ArrayAdapter<Place> {

    public PlaceAdapter(Context context, List<Place> places) {
        super(context, 0, places);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Place place = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_place, parent, false);
        }

        // Lookup views within the layout
        ImageView placeImageView = convertView.findViewById(com.isep.discoverprais.R.id.placeImageView);
        TextView placeTitleTextView = convertView.findViewById(R.id.placeTitleTextView);
        int imageResource = getContext().getResources().getIdentifier(
                place.getImg(), "raw", getContext().getPackageName());
        placeImageView.setImageResource(imageResource); // Set image resource
        placeTitleTextView.setText(place.getName()); // Set place name as title

        // Go to place screen after choosing one
        convertView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChosenPlaceActivity.class);
                intent.putExtra("position", position);
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
