package com.kackerlacka.mechie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainTools extends Fragment  {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tools, parent, false);
        ImageView hardnessConverter = view.findViewById(R.id.hardness_converter);
        ImageView dimensionlessCalculator = view.findViewById(R.id.dimensionlessNumbers_calculator);
        ImageView buckinghamPiCalulcator = view.findViewById(R.id.buckinghamPi_theorem);
        ImageView boltSizer = view.findViewById(R.id.bolt_sizer);
        Picasso.get().load(R.drawable.fracture).resize(600, 600).into(hardnessConverter);
        Picasso.get().load(R.drawable.dimensionlessnumbers).resize(600, 600).into(dimensionlessCalculator);
        Picasso.get().load(R.drawable.pi).resize(600, 600).into(buckinghamPiCalulcator);
        Picasso.get().load(R.drawable.bolts).resize(600, 600).into(boltSizer);
        return view;

    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    }

}
