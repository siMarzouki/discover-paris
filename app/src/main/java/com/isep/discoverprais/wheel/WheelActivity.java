package com.isep.discoverprais.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.isep.discoverprais.R;
import com.isep.discoverprais.services.DataManager;

import java.util.ArrayList;
import java.util.List;

public class WheelActivity extends AppCompatActivity implements SpinningWheelView.OnRotationListener<String> {

    private SpinningWheelView wheelView;

    private Button rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wheelView = (SpinningWheelView) findViewById(R.id.wheel);

        rotate = (Button) findViewById(R.id.rotate);
        List<String> items = new ArrayList<>();
        DataManager.getInstance().getPlacesList()
                .stream()
                .filter(x->!x.isVisited())
                .forEach(x->{
                    items.add(x.getName());
                });
        wheelView.setItems(
               items
        );
        wheelView.setOnRotationListener(this);

        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheelView.rotate(50, 3000, 50);
            }
        });
    }

    @Override
    public void onRotation() {
        Log.d("XXXX", "On Rotation");
    }

    @Override
    public void onStopRotation(String item) {
        CustomDialogFragment dialogFragment = new CustomDialogFragment(item);
        dialogFragment.show(getSupportFragmentManager(), "CustomDialogFragment");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}