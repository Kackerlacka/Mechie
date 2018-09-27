package com.kackerlacka.mechie;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MaterialsPropertiesMetal extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner1;
    private Spinner spinner2;
    List<String> data1 = new ArrayList<>();
    List<String> data2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materials_properties_metal);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Metal Properties");

        addListenerOnButton();

    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void spinner2Sel(final String density_, final String crystal_structure,
                            final String vickers_hardness, final String ultimate_str,
                            final String yield_str, final String break_elong, final
                            String youngs_modulus, final String bulk_modulus, final
                            String poisson_ratio, final String shear_modulus, final
                            String thermal_conductivity, final String melting_point,
                            final String boiling_point, final String cte_) {

        final TextView density = (TextView)findViewById(R.id.density_value);
        final TextView crystalStructure = (TextView)findViewById(R.id.crystalstructure_value);
        final TextView vickersHardness = (TextView)findViewById(R.id.hardness_value);
        final TextView ultimateStrength = (TextView)findViewById(R.id.ultstr_value);
        final TextView yieldStrength = (TextView)findViewById(R.id.yieldstr_value);
        final TextView breakElongation = (TextView)findViewById(R.id.elong_value);
        final TextView youngsModulus = (TextView)findViewById(R.id.youngsmodulus_value);
        final TextView bulkModulus = (TextView)findViewById(R.id.bulkmodulus_value);
        final TextView poissonRatio = (TextView)findViewById(R.id.poisson_value);
        final TextView shearModulus = (TextView)findViewById(R.id.shearmodulus_value);
        final TextView thermalConductivity = (TextView)findViewById(R.id.thermalconductivity_value);
        final TextView meltingPoint = (TextView)findViewById(R.id.meltingpoint_value);
        final TextView boilingPoint = (TextView)findViewById(R.id.boilingpoint_value);
        final TextView CTE = (TextView)findViewById(R.id.thermalexpansion_value);

        density.setText(density_);
        crystalStructure.setText(crystal_structure);
        vickersHardness.setText(vickers_hardness);
        ultimateStrength.setText(ultimate_str);
        yieldStrength.setText(yield_str);
        breakElongation.setText(break_elong);
        youngsModulus.setText(youngs_modulus);
        bulkModulus.setText(bulk_modulus);
        poissonRatio.setText(poisson_ratio);
        shearModulus.setText(shear_modulus);
        thermalConductivity.setText(thermal_conductivity);
        meltingPoint.setText(melting_point);
        boilingPoint.setText(boiling_point);
        CTE.setText(cte_);

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data1);

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data2);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        data1.add("Copper");
        data1.add("Titanium");
        data1.add("Aluminum");
        data1.add("Iron");

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()  {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(spinner1.getSelectedItemPosition()) {
                    case 0:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("Standard");
                        data2.add("Annealed");
                        data2.add("Cold-Worked");
                        data2.add("Cold Drawn");
                        adapter2.notifyDataSetChanged();
                        spinner2Sel("8.93 g/cm³", "Face-centered Cubic", "-","200 MPa",
                                "75 MPa", "40%", "130 GPa","140 GPa",
                                "0.343", "46 GPa", "394", "1083",
                                "2595", "17");
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    spinner2Sel("8.93 g/cm³", "Face-centered Cubic", "-","200 MPa",
                                            "75 MPa", "40%", "130 GPa","140 GPa",
                                            "0.343", "46 GPa", "394", "1083",
                                            "2595", "17");
                                }
                                else if(spinner2.getSelectedItemPosition() == 1) {
                                    spinner2Sel("7.764 g/cm³", "Face-centered Cubic",
                                            "100", "210 MPa", "33.3 MPa",
                                            "60%", "110 GPa", "140 GPa",
                                            "0.343", "46 GPa", "385",
                                            "1083.2 - 1083.6", "2562", "16.4");
                                }
                                else if(spinner2.getSelectedItemPosition() == 2){
                                    spinner2Sel("8.96 g/cm³", "Face-centered Cubic",
                                            "50", "-", "-",
                                            "-", "110 GPa", "140 GPa",
                                            "0.35", "46 GPa", "385",
                                            "1083.2 - 1083.6", "2562", "16.4");
                                }
                                else if(spinner2.getSelectedItemPosition() == 3){
                                    spinner2Sel("8.96 g/cm³", "Face-centered Cubic",
                                            "-", "344 MPa", "333.4 MPa",
                                            "14%", "110 GPa", "140 GPa",
                                            "0.364", "46 GPa", "385",
                                            "1083.2 - 1083.6", "2562", "16.4");
                                }
                            }
                        });
                        break;
                    case 1:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("Standard");
                        data2.add("Carbide");
                        adapter2.notifyDataSetChanged();
                        spinner2Sel("4.50 g/cm³", "Hexagonal close-packed",
                                "60", "220 MPa", "140 MPa",
                                "54%", "116 GPa", "-",
                                "0.34", "43 GPa", "17.0",
                                "1650 - 1670", "3287", "8.90");
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    spinner2Sel("4.50 g/cm³", "Hexagonal close-packed",
                                            "60", "220 MPa", "140 MPa",
                                            "54%", "116 GPa", "-",
                                            "0.34", "43 GPa", "17.0",
                                            "1650 - 1670", "3287", "8.90");
                                }
                                else if(spinner2.getSelectedItemPosition() == 1){
                                    spinner2Sel("4.94 g/cm³", "Cubic",
                                            "-", "258 MPa", "-",
                                            "-", "448 - 451 GPa", "-",
                                            "0.18 - 0.19", "110 - 193 GPa", "-",
                                            "3065", "-", "7.70");
                                }
                            }
                        });
                        break;

                    case 2:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("6061-T6 Alloy");
                        adapter2.notifyDataSetChanged();

                        spinner2Sel("2.7 g/cm³", "Face-centered Cubic",
                                "107", "310 MPa", "276 MPa",
                                "12 - 17%", "68.9 GPa", "-",
                                "0.33", "26 GPa", "167",
                                "582-652", "-", "23.6 - 25.2");
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    spinner2Sel("2.7 g/cm³", "Face-centered Cubic",
                                            "107", "310 MPa", "276 MPa",
                                            "12 - 17%", "68.9 GPa", "-",
                                            "0.33", "26 GPa", "167",
                                            "582-652", "-", "23.6 - 25.2");
                                }
                            }
                        });
                        break;

                    case 3:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("Standard");
                        adapter2.notifyDataSetChanged();

                        spinner2Sel("7.87 g/cm³", "Face-centered Cubic",
                                "150", "540 MPa", "50 MPa",
                                "-", "200 GPa", "166 GPa",
                                "0.291", "77.5 GPa", "76.2",
                                "1535", "2861", "12.2 - 24.0");
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    spinner2Sel("7.87 g/cm³", "Face-centered Cubic",
                                            "150", "540", "50",
                                            "-", "200 GPa", "166 GPa",
                                            "0.291", "77.5 GPa", "76.2",
                                            "1535", "2861", "12.2 - 24.0");
                                }
                            }
                        });
                        break;

                }
            }

        });
    }
}
