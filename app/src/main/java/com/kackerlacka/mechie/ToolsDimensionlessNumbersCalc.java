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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import katex.hourglass.in.mathlib.MathView;

public class ToolsDimensionlessNumbersCalc extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner1;
    private EditText input_field1, input_field2, input_field3, input_field4;
    private TextView input_text1, input_text2, input_text3, input_text4;
    private TextView unit1, unit2, unit3, unit4;
    private TextView resultCalc;
    private int numberSel;
    List<String> data1 = new ArrayList<>();
    private RadioGroup radioGroup;
    private int unitSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_dimensionlessnumbers);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dimensionless Numbers Calculator");

        initializeActivity();

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.metric:
                        switch(spinner1.getSelectedItemPosition()) {
                            case 0:
                                unit1.setText("kg/m³");
                                unit2.setText("m/s");
                                unit3.setText("m");
                                unit4.setText("Pa·s");
                                break;
                            case 1:
                                unit1.setText("m/s");
                                break;

                        }
                        unitSel = 0;
                        break;
                    case R.id.imperial:
                        switch(spinner1.getSelectedItemPosition()) {
                            case 0:
                                unit1.setText("slug/ft³");
                                unit2.setText("ft/s");
                                unit3.setText("ft");
                                unit4.setText("lb·s/ft²");
                                break;
                            case 1:
                                unit1.setText("ft/s");
                                break;

                        }
                        unitSel = 1;
                        break;
                }
            }
        });

        final Button button = (Button) findViewById(R.id.btn_calculate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(numberSel == 1) {
                    calculateReynolds();
                }
                else if(numberSel == 2) {
                    calculateMach();
                }
            }
        });
    }

    // Calculate the Reynolds Number
    public void calculateReynolds() {
        resultCalc = (TextView) findViewById(R.id.answer);
        String input1_string = input_field1.getText().toString();
        String input2_string = input_field2.getText().toString();
        String input3_string = input_field3.getText().toString();
        String input4_string = input_field4.getText().toString();
        double a = 0; double b = 0; double c = 0; double d = 0;

        if (!input1_string.equals("")) {
            a = Double.parseDouble(input1_string);
        }
        else {
            input_field1.setError("Enter a density");
        }
        if (!input2_string.equals("")) {
            b = Double.parseDouble(input2_string);
        }
        else {
            input_field2.setError("Enter a velocity");
        }
        if (!input3_string.equals("")) {
            c = Double.parseDouble(input3_string);
        }
        else {
            input_field3.setError("Enter a length or diameter");
        }
        if (!input4_string.equals("")) {
            d = Double.parseDouble(input4_string);
        }
        else {
            input_field4.setError("Enter a viscosity");
        }

        double result = ((double) a*b*c)/d;
        DecimalFormat formatter = new DecimalFormat("#.##");
        resultCalc.setText(formatter.format(result));

    }

    // Calculate the Mach Number
    public void calculateMach () {
        resultCalc = (TextView) findViewById(R.id.answer);
        String input1_string = input_field1.getText().toString();

        double a = 0;

        if (!input1_string.equals("")) {
            a = Double.parseDouble(input1_string);
        }
        else {
            input_field1.setError("Enter a velocity");
        }

        if(unitSel == 0) {
            double result = ((double) a)/343;
            DecimalFormat formatter = new DecimalFormat("#.##");
            resultCalc.setText(formatter.format(result));
        }
        else if(unitSel == 1) {
            double result = ((double) a)/1125;
            DecimalFormat formatter = new DecimalFormat("#.##");
            resultCalc.setText(formatter.format(result));
        }
    }

    // get the selected dropdown list value
    public void initializeActivity() {
        // Define all edit texts and textviews
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        input_field1 = (EditText) findViewById(R.id.input_field1);
        input_field2 = (EditText) findViewById(R.id.input_field2);
        input_field3 = (EditText) findViewById(R.id.input_field3);
        input_field4 = (EditText) findViewById(R.id.input_field4);
        input_text1 = (TextView) findViewById(R.id.input1);
        input_text2 = (TextView) findViewById(R.id.input2);
        input_text3 = (TextView) findViewById(R.id.input3);
        input_text4 = (TextView) findViewById(R.id.input4);
        unit1 = (TextView) findViewById(R.id.unit1);
        unit2 = (TextView) findViewById(R.id.unit2);
        unit3 = (TextView) findViewById(R.id.unit3);
        unit4 = (TextView) findViewById(R.id.unit4);
        MathView dimensionlessFormula = (MathView) findViewById(R.id.formula);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data1);

        spinner1.setAdapter(adapter1);

        data1.add("Reynold's Number (Re)");
        data1.add("Mach Number (Ma)");
        data1.add("Atwood Number (A)");
        data1.add("Biot Number (Bi)");
        data1.add("Sherwood Number (Sh)");
        data1.add("Nusselt Number (Nu)");
        data1.add("Prandtl Number (Pr)");
        data1.add("Schmidt Number (Sc)");
        data1.add("Rayleigh Number (Ra)");
        data1.add("Stanton Number (St)");
        data1.add("Strouhal Number (St)");
        data1.add("Stuart Number (N)");

        adapter1.notifyDataSetChanged();

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()  {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(spinner1.getSelectedItemPosition()) {
                    case 0:
                        numberSel = 1;
                        resetAll();
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{\\rho v D}{\\mu}$$</center>");
                        input_text1.setText("Density (ρ)");
                        input_text2.setText("Velocity (v)");
                        input_text3.setText("Length, Diameter (D)");
                        input_text4.setText("Dynamic Viscosity (µ)");
                        line1Visible(); line2Visible(); line3Visible(); line4Visible();

                        if(unitSel == 0) {
                            unit1.setText("kg/m³");
                            unit2.setText("m/s");
                            unit3.setText("m");
                            unit4.setText("Pa·s");
                        }
                        else if(unitSel == 1) {
                            unit1.setText("slug/ft³");
                            unit2.setText("ft/s");
                            unit3.setText("ft");
                            unit4.setText("lb·s/ft²");
                        }
                        break;
                    case 1:
                        numberSel = 2;
                        resetAll();
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{u}{c}$$</center>");
                        input_text1.setText("Velocity (v)");
                        unit1.setText("m/s");
                        line1Visible();

                        if(unitSel == 0) {
                            unit1.setText("m/s");
                        }
                        else if(unitSel == 1) {
                            unit1.setText("ft/s");
                        }
                        break;

                }
            }

        });
    }

    public void resetAll() {
        input_text1.setVisibility(View.GONE);
        input_text2.setVisibility(View.GONE);
        input_text3.setVisibility(View.GONE);
        input_text4.setVisibility(View.GONE);
        input_field1.setVisibility(View.GONE);
        input_field1.setError(null);
        input_field2.setVisibility(View.GONE);
        input_field2.setError(null);
        input_field3.setVisibility(View.GONE);
        input_field3.setError(null);
        input_field4.setVisibility(View.GONE);
        input_field4.setError(null);
        unit1.setVisibility(View.GONE);
        unit2.setVisibility(View.GONE);
        unit3.setVisibility(View.GONE);
        unit4.setVisibility(View.GONE);
        input_field1.getText().clear();
        input_field2.getText().clear();
        input_field3.getText().clear();
        input_field4.getText().clear();
    }

    public void line1Visible() {
        input_text1.setVisibility(View.VISIBLE);
        input_field1.setVisibility(View.VISIBLE);
        unit1.setVisibility(View.VISIBLE);
    }
    public void line2Visible() {
        input_text2.setVisibility(View.VISIBLE);
        input_field2.setVisibility(View.VISIBLE);
        unit2.setVisibility(View.VISIBLE);
    }
    public void line3Visible() {
        input_text3.setVisibility(View.VISIBLE);
        input_field3.setVisibility(View.VISIBLE);
        unit3.setVisibility(View.VISIBLE);
    }
    public void line4Visible() {
        input_text4.setVisibility(View.VISIBLE);
        input_field4.setVisibility(View.VISIBLE);
        unit4.setVisibility(View.VISIBLE);
    }
}
