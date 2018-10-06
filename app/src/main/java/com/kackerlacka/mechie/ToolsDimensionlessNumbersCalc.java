package com.kackerlacka.mechie;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import katex.hourglass.in.mathlib.MathView;

public class ToolsDimensionlessNumbersCalc extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner1;
    private EditText input1, input2, input3, input4;
    private TextView resultCalc;
    List<String> data1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_dimensionlessnumbers);

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
        getSupportActionBar().setTitle("Dimensionless Numbers Calculator");

        initializeActivity();

    }

    public void calculateButton(View view) {
        double a = Double.parseDouble(input1.getText().toString());
        double b = Double.parseDouble(input2.getText().toString());
        double c = Double.parseDouble(input3.getText().toString());
        double d = Double.parseDouble(input4.getText().toString());
        double result = ((double) a*b*c)/d;

        String resultString = Double.toString(result);
        NumberFormat formatter = new DecimalFormat("#0.00");
        String resultStringFixed = formatter.format(resultString);
        resultCalc = (TextView) findViewById(R.id.answer);
        resultCalc.setText(resultStringFixed);

    }

    // get the selected dropdown list value
    public void initializeActivity() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        input1 = (EditText) findViewById(R.id.input_field1);
        input2 = (EditText) findViewById(R.id.input_field2);
        input3 = (EditText) findViewById(R.id.input_field3);
        input4 = (EditText) findViewById(R.id.input_field4);
        MathView dimensionlessFormula = (MathView) findViewById(R.id.formula);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data1);

        spinner1.setAdapter(adapter1);

        data1.add("Reynold's Number (Re)");
        data1.add("Mach Number (Ma)");

        adapter1.notifyDataSetChanged();

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()  {

            final TextView proofStrength = (TextView)findViewById(R.id.proofstrength_value);
            final TextView tensileStrength = (TextView)findViewById(R.id.tensilestrength_value);
            final TextView yieldStrength = (TextView)findViewById(R.id.yieldstrength_value);

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(spinner1.getSelectedItemPosition()) {
                    case 0:
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{\\rho v D}{\\mu}$$</center>");
                        break;
                    case 1:
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{\\sigma v D}{\\mu}$$</center>");
                        break;

                }
            }

        });
    }
}
