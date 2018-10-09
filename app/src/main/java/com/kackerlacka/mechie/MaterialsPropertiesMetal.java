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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MaterialsPropertiesMetal extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    List<String> data1 = new ArrayList<>();
    List<String> data2 = new ArrayList<>();
    private int units;
    private RadioGroup radioGroup;
    String[] copperStandard;
    String[] copperAnnealed;
    String[] copperColdWorked;
    String[] copperColdDrawn;
    String[] titaniumStandard;
    String[] titaniumCarbide;
    String[] aluminum6061;
    String[] ironStandard;
    String[] placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materials_properties_metal);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Metal Properties");

        radioGroup = findViewById(R.id.radiogroup);

        radioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId == 1) {
                    units = 0;
                }
                else if(selectedId == 2) {
                    units = 1;
                }
        });

        addListenerOnButton();
    }

    public void spinnerSelection(String[] data) {

        final TextView density = findViewById(R.id.density_value);
        final TextView crystalStructure = findViewById(R.id.crystalstructure_value);
        final TextView vickersHardness = findViewById(R.id.hardness_value);
        final TextView ultimateStrength = findViewById(R.id.ultstr_value);
        final TextView yieldStrength = findViewById(R.id.yieldstr_value);
        final TextView breakElongation = findViewById(R.id.elong_value);
        final TextView youngsModulus = findViewById(R.id.youngsmodulus_value);
        final TextView bulkModulus = findViewById(R.id.bulkmodulus_value);
        final TextView poissonRatio = findViewById(R.id.poisson_value);
        final TextView shearModulus = findViewById(R.id.shearmodulus_value);
        final TextView thermalConductivity = findViewById(R.id.thermalconductivity_value);
        final TextView meltingPoint = findViewById(R.id.meltingpoint_value);
        final TextView boilingPoint = findViewById(R.id.boilingpoint_value);
        final TextView CTE = findViewById(R.id.thermalexpansion_value);

        density.setText(data[0]);
        crystalStructure.setText(data[1]);
        vickersHardness.setText(data[2]);
        ultimateStrength.setText(data[3]);
        yieldStrength.setText(data[4]);
        breakElongation.setText(data[5]);
        youngsModulus.setText(data[6]);
        bulkModulus.setText(data[7]);
        poissonRatio.setText(data[8]);
        shearModulus.setText(data[9]);
        thermalConductivity.setText(data[10]);
        meltingPoint.setText(data[11]);
        boilingPoint.setText(data[12]);
        CTE.setText(data[13]);
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

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

                copperStandard = getResources().getStringArray(R.array.copper_standard);
                copperAnnealed = getResources().getStringArray(R.array.copper_annealed);
                copperColdWorked = getResources().getStringArray(R.array.copper_coldWorked);
                copperColdDrawn = getResources().getStringArray(R.array.copper_coldDrawn);
                titaniumStandard = getResources().getStringArray(R.array.titanium_standard);
                titaniumCarbide = getResources().getStringArray(R.array.titanium_standard);
                aluminum6061 = getResources().getStringArray(R.array.aluminum);
                ironStandard = getResources().getStringArray(R.array.iron_standard);
                placeholder = getResources().getStringArray(R.array.placeholder);

                switch(spinner1.getSelectedItemPosition()) {
                    case 0:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("Standard");
                        data2.add("Annealed");
                        data2.add("Cold-Worked");
                        data2.add("Cold Drawn");
                        adapter2.notifyDataSetChanged();
                        spinnerSelection(copperStandard);
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    if(units == 0) {
                                        spinnerSelection(copperStandard);
                                    }
                                    else if(units == 1) {
                                        spinnerSelection(placeholder);
                                    }
                                }
                                else if(spinner2.getSelectedItemPosition() == 1) {
                                    if(units == 0) {
                                        spinnerSelection(copperAnnealed);
                                    }
                                    else if(units == 1) {
                                        spinnerSelection(placeholder);
                                    }
                                }
                                else if(spinner2.getSelectedItemPosition() == 2){
                                    if(units == 0) {
                                        spinnerSelection(copperColdWorked);
                                    }
                                    else if(units == 1) {
                                        spinnerSelection(placeholder);
                                    }
                                }
                                else if(spinner2.getSelectedItemPosition() == 3){
                                    if(units == 0) {
                                        spinnerSelection(copperColdDrawn);
                                    }
                                    else if(units == 1) {
                                        spinnerSelection(placeholder);
                                    }
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
                        spinnerSelection(titaniumStandard);
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    spinnerSelection(titaniumStandard);
                                }
                                else if(spinner2.getSelectedItemPosition() == 1){
                                    spinnerSelection(titaniumCarbide);
                                }
                            }
                        });
                        break;

                    case 2:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("6061-T6 Alloy");
                        adapter2.notifyDataSetChanged();

                        spinnerSelection(aluminum6061);
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    spinnerSelection(aluminum6061);
                                }
                            }
                        });
                        break;

                    case 3:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("Standard");
                        adapter2.notifyDataSetChanged();
                        spinnerSelection(ironStandard);
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    spinnerSelection(ironStandard);
                                }
                            }
                        });
                        break;

                }
            }

        });
    }


}
