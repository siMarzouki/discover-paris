package com.isep.discoverprais.wheel;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.isep.discoverprais.R;
import com.isep.discoverprais.models.Place;
import com.isep.discoverprais.services.DataManager;
import com.isep.discoverprais.ui.places.ChosenPlaceActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomDialogFragment extends DialogFragment {

    String choice= "";

    CustomDialogFragment(String choice){
        this.choice=choice;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout, null);

        TextView dialogText = view.findViewById(R.id.dialog_text);
        Button button1 = view.findViewById(R.id.dialog_button1);
        Button button2 = view.findViewById(R.id.dialog_button2);

        List<Place> places  =  DataManager.getInstance().getPlacesList();

        Place p =places
                .stream()
                .filter(x->x.getName()==choice)
                        .findFirst().orElse(null);

        int index= places.indexOf(p);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChosenPlaceActivity.class);
                intent.putExtra("position", index);
                getContext().startActivity(intent);
            }
        });

        dialogText.setText(choice);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        return builder.create();
    }
}
