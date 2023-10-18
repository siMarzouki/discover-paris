package com.isep.discoverprais.ui.places;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.isep.discoverprais.models.Place;
import com.isep.discoverprais.services.DataManager;

import java.util.List;

public class PlacesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PlacesViewModel() {
        mText = new MutableLiveData<>();
        List<Place> data = DataManager.getInstance().getPlacesList();
        mText.setValue("Places : "+data.size());
    }

    public LiveData<String> getText() {
        return mText;
    }
}