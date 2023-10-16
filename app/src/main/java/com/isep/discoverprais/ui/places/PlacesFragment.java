package com.isep.discoverprais.ui.places;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.isep.discoverprais.R;
import com.isep.discoverprais.databinding.FragmentPlacesBinding;
import com.isep.discoverprais.models.Place;
import com.isep.discoverprais.services.DataManager;

import java.util.List;

public class PlacesFragment extends Fragment {

    private FragmentPlacesBinding binding;
    private ListView placeListView;
    private List<Place> places;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PlacesViewModel homeViewModel =
                new ViewModelProvider(this).get(PlacesViewModel.class);

        binding = FragmentPlacesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        placeListView = root.findViewById(R.id.placesListView);

        // Load the list of places from JSON
        places =  DataManager.getInstance().getPlacesList();

        // Create a custom adapter
        PlaceAdapter adapter = new PlaceAdapter(requireContext(), places);

        // Set the adapter to the ListView
        placeListView.setAdapter(adapter);


        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
