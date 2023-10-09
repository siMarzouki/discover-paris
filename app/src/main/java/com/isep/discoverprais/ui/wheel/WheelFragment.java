package com.isep.discoverprais.ui.wheel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.isep.discoverprais.R;
import com.isep.discoverprais.databinding.FragmentWheelBinding;
import com.isep.discoverprais.wheel.WheelActivity;

public class WheelFragment extends Fragment {

    private FragmentWheelBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WheelViewModel notificationsViewModel =
                new ViewModelProvider(this).get(WheelViewModel.class);

        binding = FragmentWheelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageView wheelbanner = root.findViewById(R.id.wheelbanner);
        wheelbanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WheelActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}