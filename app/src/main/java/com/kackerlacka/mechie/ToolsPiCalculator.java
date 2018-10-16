package com.kackerlacka.mechie;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToolsPiCalculator extends AppCompatActivity {

    String[] value = new String[8];
    CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7, chk8;
    Button calculate, clear;
    TextView numbers;
    String D = "L";
    String L = "L";
    String vel = "L/T";
    String rho = "M/L3";
    String p = "M/L3";
    String mu = "M/LT";
    String eta = "L";
    String F = "ML/S2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_picalculator);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buckingham Pi Calculator");

        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);
        chk5 = findViewById(R.id.chk5);
        chk6 = findViewById(R.id.chk6);
        chk7 = findViewById(R.id.chk7);
        chk8 = findViewById(R.id.chk8);

        numbers = findViewById(R.id.numbers);

        clear = findViewById(R.id.btn_clear);
        clear.setOnClickListener((View v) -> {
            clear();
        });

        calculate = findViewById(R.id.btn_calculate);
        calculate.setOnClickListener((View v) -> {

            if(chk1.isChecked()) {
                value[0] = D;
            }
            else if(!chk1.isChecked()) {
                value[0] = "No u";
            }
            if(chk2.isChecked()) {
                value[1] = L;
            }
            else if(!chk2.isChecked()) {
                value[1] = "No u";
            }
            if(chk3.isChecked()) {
                value[2] = vel;
            }
            else if(!chk3.isChecked()) {
                value[2] = "No u";
            }
            if(chk4.isChecked()) {
                value[3] = rho;
            }
            else if(!chk4.isChecked()) {
                value[3] = "No u";
            }
            if(chk5.isChecked()) {
                value[4] = p;
            }
            else if(!chk5.isChecked()) {
                value[4] = "No u";
            }
            if(chk6.isChecked()) {
                value[5] = mu;
            }
            else if(!chk6.isChecked()) {
                value[5] = "No u";
            }
            if(chk7.isChecked()) {
                value[6] = eta;
            }
            else if(!chk7.isChecked()) {
                value[6] = "No u";
            }
            if(chk8.isChecked()) {
                value[7] = F;
            }
            else if(!chk8.isChecked()) {
                value[7] = "No u";
            }

            StringBuilder builder = new StringBuilder();
            for (String details : value) {
                builder.append(details + "\n");
            }

            numbers.setText(builder.toString());
        });

    }

    public void clear() {
        chk1.setChecked(false);
        chk2.setChecked(false);
        chk3.setChecked(false);
        chk4.setChecked(false);
        chk5.setChecked(false);
        chk6.setChecked(false);
        chk7.setChecked(false);
        chk8.setChecked(false);
    }
}
