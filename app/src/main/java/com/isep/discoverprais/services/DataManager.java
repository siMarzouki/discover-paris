package com.isep.discoverprais.services;

import android.content.Context;
import com.isep.discoverprais.R;
import com.isep.discoverprais.models.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;


public class DataManager {
    private static DataManager instance;
    private List<Place> places;

    private DataManager() {
        places = new ArrayList<>();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void loadData(Context context) {
        places.clear();

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.places);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String img = jsonObject.getString("img");
                String description = jsonObject.getString("description");
                double latitude = jsonObject.getDouble("latitude");
                double longitude = jsonObject.getDouble("longitude");
                Place place = new Place(name, description,img,  longitude, latitude,false);
                places.add(place);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public List<Place> getPlacesList() {
        return places;
    }
}
