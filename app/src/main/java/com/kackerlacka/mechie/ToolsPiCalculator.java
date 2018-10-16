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

    ArrayList<String> value = new ArrayList<String>();
    ArrayList<String> dim = new ArrayList<String>();
    Button calculate, clear;
    TextView kValue, rValue, piValue;
    int r = 0, pi = 0;
    String D = "L";
    String L = "L";
    String vel = "L/T";
    String rho = "M/L3";
    String p = "M/(LT2)";
    String mu = "M/LT";
    String eta = "L";
    String F = "ML/T2";

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

        kValue = findViewById(R.id.variables);
        rValue = findViewById(R.id.dimensional_quantities);
        piValue = findViewById(R.id.pi_terms);

        clear = findViewById(R.id.btn_clear);
        clear.setOnClickListener((View v) -> {
            clear();
        });

        dim.add(D);
        dim.add(L);
        dim.add(vel);
        dim.add(rho);
        dim.add(p);
        dim.add(mu);
        dim.add(eta);
        dim.add(F);

        calculate = findViewById(R.id.btn_calculate);
        calculate.setOnClickListener((View v) -> {

            value = new ArrayList<String>();
            for(int i = 0; i < 8; i++) {
                int checkID = getResources().getIdentifier("chk"+i, "id", getPackageName());
                CheckBox check = findViewById(checkID);

                if(check.isChecked()) {
                    value.add(dim.get(i));
                }
            }

            int sizeInt = value.size();
            String size = Integer.toString(sizeInt);
            kValue.setText(size);

            for (String s : value) {
                if (s.contains("M") && s.contains("L") && s.contains("T")) {
                    r = 3;
                }
                else if((s.contains("M") && s.contains("L")) || (s.contains("T") && s.contains("L")) || (s.contains("M") && s.contains("T"))) {
                    r = 2;
                }
                else if(s.contains("L") || s.contains("T") || s.contains("M")) {
                    r = 1;
                }
            }

            rValue.setText(Integer.toString(r));

            pi = sizeInt - r;
            piValue.setText(Integer.toString(pi));

        });

    }

    public void clear() {
        for(int k = 0; k < 8; k++) {
            int checkID = getResources().getIdentifier("chk"+k, "id", getPackageName());
            CheckBox check = findViewById(checkID);
            check.setChecked(false);
        }
        kValue.setText("");
        rValue.setText("");
        piValue.setText("");
        r = 0;
        pi = 0;
    }
}
