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
    private int unitSel;
    private RadioGroup radioGroup;
    String[] copperStandardMetric;
    String[] copperAnnealedMetric;
    String[] copperColdWorkedMetric;
    String[] copperColdDrawnMetric;
    String[] titaniumStandardMetric;
    String[] titaniumCarbideMetric;
    String[] aluminum6061Metric;
    String[] ironStandardMetric;
    String[] copperStandardImperial;
    String[] copperAnnealedImperial;
    String[] copperColdWorkedImperial;
    String[] copperColdDrawnImperial;
    String[] titaniumStandardImperial;
    String[] titaniumCarbideImperial;
    String[] aluminum6061Imperial;
    String[] ironStandardImperial;
    String[] steel1005Metric;
    String[] steel1005Imperial;
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
            switch(checkedId) {
                case R.id.metric:
                    unitSel = 0;
                    switch(spinner1.getSelectedItem().toString()) {
                        case "Copper":
                            switch(spinner2.getSelectedItem().toString()) {
                                case "Standard":
                                    spinnerSelection(copperStandardMetric);
                                    break;
                                case "Annealed":
                                    spinnerSelection(copperAnnealedMetric);
                                    break;
                                case "Cold-Worked":
                                    spinnerSelection(copperColdWorkedMetric);
                                    break;
                                case "Cold Drawn":
                                    spinnerSelection(copperColdDrawnMetric);
                                    break;
                            }
                            break;
                        case "Titanium":
                            switch(spinner2.getSelectedItem().toString()) {
                                case "Standard":
                                    spinnerSelection(titaniumStandardMetric);
                                    break;
                                case "Carbide":
                                    spinnerSelection(titaniumCarbideMetric);
                                    break;
                            }
                            break;
                        case "Aluminum":
                            switch(spinner2.getSelectedItem().toString()) {
                                case "6061-T6 Alloy":
                                    spinnerSelection(aluminum6061Metric);
                                    break;
                            }
                            break;
                        case "Iron":
                            switch(spinner2.getSelectedItem().toString()) {
                                case "Standard":
                                    spinnerSelection(ironStandardMetric);
                                    break;
                            }
                            break;
                        case "Steel":
                            switch(spinner2.getSelectedItem().toString()) {
                                case "AISI 1005":
                                    spinnerSelection(steel1005Metric);
                                    break;
                            }
                            break;

                    }
                    break;
                case R.id.imperial:
                    unitSel = 1;
                    switch(spinner1.getSelectedItem().toString()) {
                        case "Copper":
                            switch(spinner2.getSelectedItem().toString()) {
                                case "Standard":
                                    spinnerSelection(copperStandardImperial);
                                    break;
                                case "Annealed":
                                    spinnerSelection(copperAnnealedImperial);
                                    break;
                                case "Cold-Worked":
                                    spinnerSelection(copperColdWorkedImperial);
                                    break;
                                case "Cold Drawn":
                                    spinnerSelection(copperColdDrawnImperial);
                                    break;
                            }
                            break;
                        case "Titanium":
                            switch(spinner2.getSelectedItem().toString()) {
                                case "Standard":
                                    spinnerSelection(titaniumStandardImperial);
                                    break;
                                case "Carbide":
                                    spinnerSelection(titaniumCarbideImperial);
                                    break;
                            }
                            break;
                        case "Aluminum":
                            switch(spinner2.getSelectedItem().toString()) {
                                case "6061-T6 Alloy":
                                    spinnerSelection(aluminum6061Imperial);
                                    break;
                            }
                            break;
                        case "Iron":
                            switch(spinner2.getSelectedItem().toString()) {
                                case "Standard":
                                    spinnerSelection(ironStandardImperial);
                                    break;
                            }
                            break;
                        case "Steel":
                            switch(spinner2.getSelectedItem().toString()) {
                                case "Standard":
                                    spinnerSelection(steel1005Imperial);
                                    break;
                            }
                            break;

                    }
                    break;
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
        data1.add("Steel");

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()  {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                copperStandardMetric = getResources().getStringArray(R.array.copper_standard_metric);
                copperAnnealedMetric = getResources().getStringArray(R.array.copper_annealed_metric);
                copperColdWorkedMetric = getResources().getStringArray(R.array.copper_coldWorked_metric);
                copperColdDrawnMetric = getResources().getStringArray(R.array.copper_coldDrawn_metric);
                titaniumStandardMetric = getResources().getStringArray(R.array.titanium_standard_metric);
                titaniumCarbideMetric = getResources().getStringArray(R.array.titanium_carbide_metric);
                aluminum6061Metric = getResources().getStringArray(R.array.aluminum_6061_metric);
                ironStandardMetric = getResources().getStringArray(R.array.iron_standard_metric);
                steel1005Metric = getResources().getStringArray(R.array.steel_1005_metric);

                copperStandardImperial = getResources().getStringArray(R.array.copper_standard_imperial);
                copperAnnealedImperial = getResources().getStringArray(R.array.copper_annealed_imperial);
                copperColdWorkedImperial = getResources().getStringArray(R.array.copper_coldWorked_imperial);
                copperColdDrawnImperial = getResources().getStringArray(R.array.copper_coldDrawn_imperial);
                titaniumStandardImperial = getResources().getStringArray(R.array.titanium_standard_imperial);
                titaniumCarbideImperial = getResources().getStringArray(R.array.titanium_carbide_imperial);
                aluminum6061Imperial = getResources().getStringArray(R.array.aluminum_6061_imperial);
                ironStandardImperial = getResources().getStringArray(R.array.iron_standard_imperial);
                steel1005Imperial = getResources().getStringArray(R.array.steel_1005_imperial);

                switch(spinner1.getSelectedItemPosition()) {
                    case 0:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("Standard");
                        data2.add("Annealed");
                        data2.add("Cold-Worked");
                        data2.add("Cold Drawn");
                        adapter2.notifyDataSetChanged();
                        if(unitSel == 0) {
                            spinnerSelection(copperStandardMetric);
                        }
                        else if(unitSel == 1) {
                            spinnerSelection(copperStandardImperial);
                        }
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    if(unitSel == 0) {
                                        spinnerSelection(copperStandardMetric);
                                    }
                                    else if(unitSel == 1) {
                                        spinnerSelection(copperStandardImperial);
                                    }
                                }
                                else if(spinner2.getSelectedItemPosition() == 1) {
                                    if(unitSel == 0) {
                                        spinnerSelection(copperAnnealedMetric);
                                    }
                                    else if(unitSel == 1) {
                                        spinnerSelection(copperAnnealedImperial);
                                    }
                                }
                                else if(spinner2.getSelectedItemPosition() == 2){
                                    if(unitSel == 0) {
                                        spinnerSelection(copperColdWorkedMetric);
                                    }
                                    else if(unitSel == 1) {
                                        spinnerSelection(copperColdWorkedImperial);
                                    }
                                }
                                else if(spinner2.getSelectedItemPosition() == 3){
                                    if(unitSel == 0) {
                                        spinnerSelection(copperColdDrawnMetric);
                                    }
                                    else if(unitSel == 1) {
                                        spinnerSelection(copperColdDrawnImperial);
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
                        if(unitSel == 0) {
                            spinnerSelection(titaniumStandardMetric);
                        }
                        else if(unitSel == 1) {
                            spinnerSelection(titaniumStandardImperial);
                        }
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    if(unitSel == 0) {
                                        spinnerSelection(copperColdDrawnMetric);
                                    }
                                    else if(unitSel == 1) {
                                        spinnerSelection(copperColdDrawnImperial);
                                    }
                                }
                                else if(spinner2.getSelectedItemPosition() == 1){
                                    if(unitSel == 0) {
                                        spinnerSelection(titaniumCarbideMetric);
                                    }
                                    else if(unitSel == 1) {
                                        spinnerSelection(titaniumCarbideImperial);
                                    }
                                }
                            }
                        });
                        break;

                    case 2:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("6061-T6 Alloy");
                        adapter2.notifyDataSetChanged();
                        if(unitSel == 0) {
                            spinnerSelection(aluminum6061Metric);
                        }
                        else if(unitSel == 1) {
                            spinnerSelection(aluminum6061Imperial);
                        }
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    if(unitSel == 0) {
                                        spinnerSelection(aluminum6061Metric);
                                    }
                                    else if(unitSel == 1) {
                                        spinnerSelection(aluminum6061Imperial);
                                    }
                                }
                            }
                        });
                        break;

                    case 3:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("Standard");
                        adapter2.notifyDataSetChanged();
                        if(unitSel == 0) {
                            spinnerSelection(ironStandardMetric);
                        }
                        else if(unitSel == 1) {
                            spinnerSelection(ironStandardImperial);
                        }
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    if(unitSel == 0) {
                                        spinnerSelection(ironStandardMetric);
                                    }
                                    else if(unitSel == 1) {
                                        spinnerSelection(ironStandardImperial);
                                    }
                                }
                            }
                        });
                        break;
                    case 4:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("AISI 1005");
                        adapter2.notifyDataSetChanged();
                        if(unitSel == 0) {
                            spinnerSelection(steel1005Metric);
                        }
                        else if(unitSel == 1) {
                            spinnerSelection(steel1005Imperial);
                        }
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    if(unitSel == 0) {
                                        spinnerSelection(steel1005Metric);
                                    }
                                    else if(unitSel == 1) {
                                        spinnerSelection(steel1005Imperial);
                                    }
                                }
                            }
                        });
                        break;

                }
            }

        });
    }


}
