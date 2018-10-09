package com.kackerlacka.mechie;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToolsHardnessConverter extends AppCompatActivity {

    private Spinner spinner1;
    List<String> data1 = new ArrayList<>();
    private EditText hardnessInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_hardnessconverter);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hardness Converter");

        addListenerOnButton();

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = findViewById(R.id.spinner1);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data1);

        spinner1.setAdapter(adapter1);

        data1.add("Brinell HB");
        data1.add("Vickers HV");
        data1.add("Rockwell A");
        data1.add("Rockwell B");
        data1.add("Rockwell C");
        data1.add("Knoop");

        hardnessInput = findViewById(R.id.hardness_input);

        adapter1.notifyDataSetChanged();

        final Button button = findViewById(R.id.btn_convert);
        final TextView brinell = findViewById(R.id.brinell_value);
        final TextView vickers = findViewById(R.id.vickers_value);
        final TextView rockwellA = findViewById(R.id.rwa_value);
        final TextView rockwellB = findViewById(R.id.rwb_value);
        final TextView rockwellC = findViewById(R.id.rwc_value);
        final TextView knoop = findViewById(R.id.knoop_value);

        button.setOnClickListener((View v) -> {
                switch (spinner1.getSelectedItemPosition()) {
                    case 0:
                        resetAll();
                        brinell.setText("-");
                        String input = hardnessInput.getText().toString();
                        double a = Double.parseDouble(input);
                        if(a > 722 && a < 739) {

                        }
                        break;
                    case 1:
                        resetAll();
                        vickers.setText("-");
                        break;
                    case 2:
                        resetAll();
                        rockwellA.setText("-");
                        break;
                    case 3:
                        resetAll();
                        rockwellB.setText("-");
                        break;
                    case 4:
                        resetAll();
                        rockwellC.setText("-");
                        break;
                    case 5:
                        resetAll();
                        knoop.setText("-");
                        break;
                }
        });
    }

    public void resetAll() {
        final TextView brinell = findViewById(R.id.brinell_value);
        final TextView vickers = findViewById(R.id.vickers_value);
        final TextView rockwellA = findViewById(R.id.rwa_value);
        final TextView rockwellB = findViewById(R.id.rwb_value);
        final TextView rockwellC = findViewById(R.id.rwc_value);
        final TextView knoop = findViewById(R.id.knoop_value);

        brinell.setText("");
        vickers.setText("");
        rockwellA.setText("");
        rockwellB.setText("");
        rockwellC.setText("");
        knoop.setText("");
    }
}
