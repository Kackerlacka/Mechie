package com.kackerlacka.mechie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainConcepts extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_concepts, parent, false);
        ImageView mechanics = view.findViewById(R.id.mechanics);
        ImageView materials = view.findViewById(R.id.materials);
        ImageView chemistry = view.findViewById(R.id.chemistry);
        ImageView physics = view.findViewById(R.id.physics);
        ImageView thermalEngineering = view.findViewById(R.id.thermalEngineering);
        ImageView mathematics = view.findViewById(R.id.mathematics);
        Picasso.get().load(R.drawable.gears).resize(600, 600).into(mechanics);
        Picasso.get().load(R.drawable.materials).resize(600, 600).into(materials);
        Picasso.get().load(R.drawable.chemistry).resize(600, 600).into(chemistry);
        Picasso.get().load(R.drawable.physics).resize(600, 600).into(physics);
        Picasso.get().load(R.drawable.thermal).resize(600, 600).into(thermalEngineering);
        Picasso.get().load(R.drawable.mathematics).resize(600, 600).into(mathematics);
        return view;

    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }
}
