package com.isep.discoverprais.ui.wheel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WheelViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WheelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Wheel !!");

    }




    public LiveData<String> getText() {
        return mText;
    }
}