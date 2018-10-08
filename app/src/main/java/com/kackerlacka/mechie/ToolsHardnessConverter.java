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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToolsHardnessConverter extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner1;
    List<String> data1 = new ArrayList<>();
    private EditText hardnessInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_hardnessconverter);

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
        getSupportActionBar().setTitle("Hardness Converter");

        addListenerOnButton();

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data1);

        spinner1.setAdapter(adapter1);

        data1.add("Brinell HB");
        data1.add("Vickers HV");
        data1.add("Rockwell A");
        data1.add("Rockwell B");
        data1.add("Rockwell C");
        data1.add("Knoop");

        hardnessInput = (EditText) findViewById(R.id.hardness_input);

        adapter1.notifyDataSetChanged();

        final Button button = (Button) findViewById(R.id.btn_convert);
        final TextView brinell = (TextView) findViewById(R.id.brinell_value);
        final TextView vickers = (TextView) findViewById(R.id.vickers_value);
        final TextView rockwellA = (TextView) findViewById(R.id.rwa_value);
        final TextView rockwellB = (TextView) findViewById(R.id.rwb_value);
        final TextView rockwellC = (TextView) findViewById(R.id.rwc_value);
        final TextView knoop = (TextView) findViewById(R.id.knoop_value);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (spinner1.getSelectedItemPosition()) {
                    case 0:
                        brinell.setText("-");
                        hardnessInput.getText().toString();
                        break;
                    case 1:
                        vickers.setText("-");
                        break;
                    case 2:
                        rockwellA.setText("-");
                        break;
                    case 3:
                        rockwellB.setText("-");
                        break;
                    case 4:
                        rockwellC.setText("-");
                        break;
                    case 5:
                        knoop.setText("-");

                }
            }

        });
    }
}
