package com.kackerlacka.mechie;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainMaterials extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_materials, parent, false);
        ImageView metalProperties = view.findViewById(R.id.metal_properties);
        ImageView fluidProperties = view.findViewById(R.id.fluid_properties);
        Picasso.get().load(R.drawable.metal).resize(600, 600).into(metalProperties);
        Picasso.get().load(R.drawable.fluids).resize(600, 600).into(fluidProperties);
        return view;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    }
}
