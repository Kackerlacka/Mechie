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

public class MaterialsPropertiesFluids extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner1;
    List<String> data1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materials_properties_fluids);

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
        getSupportActionBar().setTitle("Fluid Properties");

        addListenerOnButton();

    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void spinner2Sel(final String density_, final String boiling_temp) {

        final TextView density = (TextView)findViewById(R.id.density_value);
        final TextView boilingTemp = (TextView)findViewById(R.id.boilingtemp_value);

        density.setText(density_);
        boilingTemp.setText(boiling_temp);

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data1);

        spinner1.setAdapter(adapter1);

        data1.add("Water");

        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()  {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(spinner1.getSelectedItemPosition()) {
                    case 0:
                        spinner2Sel("999.98 kg/m³", "99.97 °C");
                        break;
                    case 1:
                        spinner2Sel("8.93", "Face-centered Cubic");
                        break;

                    case 2:
                        spinner2Sel("8.93", "Face-centered Cubic");
                        break;

                    case 3:
                        spinner2Sel("8.93", "Face-centered Cubic");
                        break;

                }
            }

        });
    }
}
