package com.kackerlacka.mechie;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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

    private Spinner spinner1;
    private EditText input_field1, input_field2, input_field3, input_field4;
    private TextView input_text1, input_text2, input_text3, input_text4;
    private TextView unit1, unit2, unit3, unit4;
    private Button button1, button2, button3, button4;
    private TextView resultCalc;
    private int numberSel;
    List<String> data1 = new ArrayList<>();
    private RadioGroup radioGroup;
    private int unitSel;
    Context context = this;
    String[] fluidsDensityMetric;
    String[] fluidsDensityImperial;
    String[] fluidsDynamicViscosityMetric;
    String[] fluidsDynamicViscosityImperial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_dimensionlessnumbers);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dimensionless Numbers Calculator");

        initializeActivity();

        radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
                switch(checkedId) {
                    case R.id.metric:
                        clearInputs();
                        switch(spinner1.getSelectedItem().toString()) {
                            case "Reynold's Number (Re)":
                                unit1.setText("kg/m³");
                                unit2.setText("m/s");
                                unit3.setText("m");
                                unit4.setText("Pa·s");
                                break;
                            case "Mach Number (Ma)":
                                unit1.setText("m/s");
                                break;
                            case "Atwood Number (A)":
                                unit1.setText("kg/m³");
                                unit2.setText("kg/m³");
                                break;
                            case "Biot Number (Bi)":
                                unit1.setText("m");
                                unit2.setText("W/(m²⋅K)");
                                unit3.setText("W/(m⋅K)");
                                break;
                            case "Sherwood Number (Sh)":
                                unit1.setText("m⋅s⁻¹");
                                unit2.setText("m²⋅s⁻¹");
                                unit3.setText("m");
                                break;
                            case "Nusselt Number (Nu)":
                                unit1.setText("W/(m²⋅K)");
                                unit2.setText("m");
                                unit3.setText("W/(m⋅K)");
                                break;
                            case "Prandtl Number (Pr)":
                                unit1.setText("m²/s");
                                unit2.setText("m²/s");
                                break;

                        }
                        unitSel = 0;
                        break;
                    case R.id.imperial:
                        clearInputs();
                        switch(spinner1.getSelectedItem().toString()) {
                            case "Reynold's Number (Re)":
                                unit1.setText("lb/ft³");
                                unit2.setText("ft/s");
                                unit3.setText("ft");
                                unit4.setText(R.string.units_imperial_dynamicViscosity);
                                break;
                            case "Mach Number (Ma)":
                                unit1.setText("ft/s");
                                break;
                            case "Atwood Number (A)":
                                unit1.setText("lb/ft³");
                                unit2.setText("lb/ft³");
                                break;
                            case "Biot Number (Bi)":
                                unit1.setText("ft");
                                unit2.setText("BTU/(s⋅ft²⋅°F)");
                                unit3.setText("BTU/(hr⋅ft⋅°F)");
                                break;
                            case "Sherwood Number (Sh)":
                                unit1.setText("ft⋅s⁻¹");
                                unit2.setText("ft²⋅s⁻¹");
                                unit3.setText("ft");
                                break;
                            case "Nusselt Number (Nu)":
                                unit1.setText("BTU/(s⋅ft²⋅°F)");
                                unit2.setText("ft");
                                unit3.setText("BTU/(hr⋅ft⋅°F)");
                                break;
                            case "Prandtl Number (Pr)":
                                unit1.setText("ft²/s");
                                unit2.setText("ft²/s");
                                break;

                        }
                        unitSel = 1;
                        break;
                }
        });

        Button clearBtn = findViewById(R.id.btn_clear);
        clearBtn.setOnClickListener((View v) -> {
            clearInputs();
        });

        final Button button = findViewById(R.id.btn_calculate);
        button.setOnClickListener((View v) -> {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                if(numberSel == 1) {
                    calculateReynolds();
                }
                else if(numberSel == 2) {
                    calculateMach();
                }
                else if(numberSel == 3) {
                    calculateAtwood();
                }
                else if(numberSel == 4) {
                    calculateBiot();
                }
                else if(numberSel == 5) {
                    calculateSherwood();
                }
                else if(numberSel == 6) {
                    calculateNusselt();
                }
                else if(numberSel == 7) {
                    calculatePrandtl();
                }
            });

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener((View v) -> {
            switch(spinner1.getSelectedItem().toString()) {
                case "Reynold's Number (Re)":
                    input_field1.setError(null);
                    if(unitSel == 0) {
                        displayAlert(fluidsDensityMetric, input_field1, "1049", "1.205",
                                "924", "1000", "1022");
                    }
                    else if(unitSel == 1) {
                        displayAlert(fluidsDensityImperial, input_field1, "65.5", "0.0752",
                                "57.7", "62.4", "63.8");
                    }
                    break;

            }
        });

        button4.setOnClickListener((View v) -> {
            switch(spinner1.getSelectedItem().toString()) {
                case "Reynold's Number (Re)":
                    input_field4.setError(null);
                    if(unitSel == 0) {
                        displayAlert(fluidsDynamicViscosityMetric, input_field4, "0.001155",
                                "0.00001722","0.0162", "0.00089", "0.00011");
                    }
                    else if(unitSel == 1) {
                        displayAlert(fluidsDynamicViscosityImperial, input_field4, "0.000776",
                                "0.00001157","0.0109", "0.0006", "0.000074");
                    }
                    break;

            }
        });
    }

    public void displayAlert(String[] fluids, EditText inputField, String num1, String num2, String num3,
                             String num4, String num5) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select a Fluid");
        // add a list
        builder.setItems(fluids, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        inputField.setText(num1);
                        break;
                    case 1:
                        inputField.setText(num2);
                        break;
                    case 2:
                        inputField.setText(num3);
                        break;
                    case 3:
                        inputField.setText(num4);
                        break;
                    case 4:
                        inputField.setText(num5);
                        break;
                }
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Calculate the Reynolds Number
    public void calculateReynolds() {
        resultCalc = findViewById(R.id.answer);
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

        double result = (a*b*c)/d;
        DecimalFormat formatter = new DecimalFormat("#.##");
        resultCalc.setText(formatter.format(result));

    }

    // Calculate the Mach Number
    public void calculateMach () {
        resultCalc = findViewById(R.id.answer);
        String input1_string = input_field1.getText().toString();

        double a = 0;

        if (!input1_string.equals("")) {
            a = Double.parseDouble(input1_string);
        }
        else {
            input_field1.setError("Enter a velocity");
        }

        if(unitSel == 0) {
            double result = a/343;
            DecimalFormat formatter = new DecimalFormat("#.##");
            resultCalc.setText(formatter.format(result));
        }
        else if(unitSel == 1) {
            double result = a/1125;
            DecimalFormat formatter = new DecimalFormat("#.##");
            resultCalc.setText(formatter.format(result));
        }
    }

    // Calculate the Atwood Number
    public void calculateAtwood() {
        resultCalc = findViewById(R.id.answer);
        String input1_string = input_field1.getText().toString();
        String input2_string = input_field2.getText().toString();
        double a = 0; double b = 0;

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
            input_field2.setError("Enter a density");
        }

        double result = (a - b)/(a + b);
        DecimalFormat formatter = new DecimalFormat("#.##");
        resultCalc.setText(formatter.format(result));

    }

    // Calculate the Biot Number
    public void calculateBiot() {
        resultCalc = (TextView) findViewById(R.id.answer);
        String input1_string = input_field1.getText().toString();
        String input2_string = input_field2.getText().toString();
        String input3_string = input_field3.getText().toString();
        double a = 0; double b = 0; double c = 0;

        if (!input1_string.equals("")) {
            a = Double.parseDouble(input1_string);
        }
        else {
            input_field1.setError("Enter a length");
        }
        if (!input2_string.equals("")) {
            b = Double.parseDouble(input2_string);
        }
        else {
            input_field2.setError("Enter a heat transfer coefficient");
        }
        if (!input3_string.equals("")) {
            b = Double.parseDouble(input3_string);
        }
        else {
            input_field3.setError("Enter a thermal conductivity");
        }

        double result = ((double) a*b)/((double) c);
        DecimalFormat formatter = new DecimalFormat("#.##");
        resultCalc.setText(formatter.format(result));

    }

    // Calculate the Sherwood Number
    public void calculateSherwood() {
        resultCalc = (TextView) findViewById(R.id.answer);
        String input1_string = input_field1.getText().toString();
        String input2_string = input_field2.getText().toString();
        String input3_string = input_field3.getText().toString();
        double a = 0; double b = 0; double c = 0;

        if (!input1_string.equals("")) {
            a = Double.parseDouble(input1_string);
        }
        else {
            input_field1.setError("Enter a length");
        }
        if (!input2_string.equals("")) {
            b = Double.parseDouble(input2_string);
        }
        else {
            input_field2.setError("Enter a mass diffusivity");
        }
        if (!input3_string.equals("")) {
            b = Double.parseDouble(input3_string);
        }
        else {
            input_field3.setError("Enter a convective mass transfer film coefficient");
        }

        double result = ((double) a)/((double) b*c);
        DecimalFormat formatter = new DecimalFormat("#.##");
        resultCalc.setText(formatter.format(result));

    }

    // Calculate the Sherwood Number
    public void calculateNusselt() {
        resultCalc = (TextView) findViewById(R.id.answer);
        String input1_string = input_field1.getText().toString();
        String input2_string = input_field2.getText().toString();
        String input3_string = input_field3.getText().toString();
        double a = 0; double b = 0; double c = 0;

        if (!input1_string.equals("")) {
            a = Double.parseDouble(input1_string);
        }
        else {
            input_field1.setError("Enter a convective heat transfer coefficient");
        }
        if (!input2_string.equals("")) {
            b = Double.parseDouble(input2_string);
        }
        else {
            input_field2.setError("Enter a mass characteristic length");
        }
        if (!input3_string.equals("")) {
            b = Double.parseDouble(input3_string);
        }
        else {
            input_field3.setError("Enter a thermal conductivity");
        }

        double result = ((double) a*b)/((double) c);
        DecimalFormat formatter = new DecimalFormat("#.##");
        resultCalc.setText(formatter.format(result));

    }

    // Calculate the Prandtl Number
    public void calculatePrandtl() {
        resultCalc = findViewById(R.id.answer);
        String input1_string = input_field1.getText().toString();
        String input2_string = input_field2.getText().toString();
        double a = 0; double b = 0;

        if (!input1_string.equals("")) {
            a = Double.parseDouble(input1_string);
        }
        else {
            input_field1.setError("Enter a kinematic viscosity");
        }
        if (!input2_string.equals("")) {
            b = Double.parseDouble(input2_string);
        }
        else {
            input_field2.setError("Enter a thermal diffusivity");
        }

        double result = ((double) a)/((double) b);
        DecimalFormat formatter = new DecimalFormat("#.##");
        resultCalc.setText(formatter.format(result));

    }

    // get the selected dropdown list value
    public void initializeActivity() {
        // Define all edit texts and textviews
        spinner1 = findViewById(R.id.spinner1);
        input_field1 = findViewById(R.id.input_field1);
        input_field2 = findViewById(R.id.input_field2);
        input_field3 = findViewById(R.id.input_field3);
        input_field4 = findViewById(R.id.input_field4);
        input_text1 = findViewById(R.id.input1);
        input_text2 = findViewById(R.id.input2);
        input_text3 = findViewById(R.id.input3);
        input_text4 = findViewById(R.id.input4);
        unit1 = findViewById(R.id.unit1);
        unit2 = findViewById(R.id.unit2);
        unit3 = findViewById(R.id.unit3);
        unit4 = findViewById(R.id.unit4);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        MathView dimensionlessFormula = findViewById(R.id.formula);
        radioGroup = findViewById(R.id.radiogroup);

        fluidsDensityMetric = getResources().getStringArray(R.array.fluids_density_metric);
        fluidsDensityImperial = getResources().getStringArray(R.array.fluids_density_imperial);
        fluidsDynamicViscosityMetric = getResources().getStringArray(R.array.fluids_dynamicViscosity_metric);
        fluidsDynamicViscosityImperial = getResources().getStringArray(R.array.fluids_dynamicViscosity_imperial);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data1);

        spinner1.setAdapter(adapter1);

        data1.add("Reynold's Number (Re)");

        data1.add("[X]Abbe Number (V)");
        data1.add("Atwood Number (A)");

        data1.add("[X]Bagnold Number (Ba)");
        data1.add("[X]Behan Number (Be)");
        data1.add("[X]Bingham Number (Bm)");
        data1.add("Biot Number (Bi)");
        data1.add("[X]Blake Number (Bl)");
        data1.add("[X]Bodenstein Number (Bd)");
        data1.add("[X]Bond Number (Bo)");
        data1.add("[X]Brinkman Number (Br)");
        data1.add("[X]Brownell-Katz Number (NBK)");

        data1.add("[X]Capillary Number (Ca)");
        data1.add("[X]Chandrasekhar Number (Q)");
        data1.add("[X]Cohesion Number (Coh)");
        data1.add("[X]Courant-Friedrich-Levy No. (C)");

        data1.add("[X]Damping Ratio (ζ)");
        data1.add("[X]Darcy Number (Da)");
        data1.add("[X]Dean Number (D)");
        data1.add("[X]Deborah Number (De)");
        data1.add("[X]Drag Coefficient (C𝒹)");
        data1.add("[X]Dukhin Number (Du)");

        data1.add("[X]Eckert Number (Ec)");
        data1.add("[X]Ekman Number (Ek)");
        data1.add("[X]Eötvös Number (Eo)");
        data1.add("[X]Ericksen Number (Er)");
        data1.add("[X]Euler Number (Eu)");
        data1.add("[X]Excess Temperature Coefficient (Ɵᵣ)");

        data1.add("[X]Fine-structure Constant (α)");
        data1.add("[X]F-Number (f)");
        data1.add("[X]Föppl–von Kármán Number (γ)");
        data1.add("[X]Fourier Number (Fo)");
        data1.add("[X]Fresnel Number (F)");
        data1.add("[X]Froude Number (Fr)");

        data1.add("[X]Galilei Number (Ga)");
        data1.add("[X]Görtler Number (G)");
        data1.add("[X]Graetz Number (Gz)");
        data1.add("[X]Grashof Number (Gr)");
        data1.add("[X]Gravitational Coupling Constant (αG)");

        data1.add("[X]Hatta Number (Ha)");
        data1.add("[X]Hagen Number (Hg)");
        data1.add("[X]Havnes Parameter (Pₕ)");
        data1.add("[X]Helmholtz Number (He)");
        data1.add("[X]Hydraulic Gradient (i)");

        data1.add("[X]Iribarren Number (Ir)");

        data1.add("[X]Jakob Number (Ja)");

        data1.add("[X]Karlovitz Number (Ka)");
        data1.add("[X]Keulegan-Carpenter Number (Kc)");
        data1.add("[X]Knudsen Number (Kn)");
        data1.add("[X]Kutateladze Number (Ku)");

        data1.add("[X]Laplace Number (La)");
        data1.add("[X]Lewis Number (Le)");
        data1.add("[X]Lift Coefficient (Cₗ)");
        data1.add("[X]Lockhart-Martinelli Parameter (X)");
        data1.add("[X]Lundquist Number (S)");

        data1.add("[X]Mach Number (Ma)");
        data1.add("[X]Magnetic Reynolds Number (Rₘ)");
        data1.add("[X]Markstein Number (M)");
        data1.add("[X]Morton Number (Mo)");

        data1.add("Nusselt Number (Nu)");

        data1.add("[X]Ohnesorge Number (Oh)");

        data1.add("[X]Péclet Number (Pe)");
        data1.add("[X]Perveance (K)");
        data1.add("[X]Plasma Physics Beta (β)");
        data1.add("[X]Porosity (φ)");
        data1.add("Prandtl Number (Pr)");
        data1.add("[X]Pressure Coefficient (Cₚ)");

        data1.add("[X]Rayleigh Number (Ra)");
        data1.add("[X]Refractive Index (n)");
        data1.add("[X]Richardson Number (Ri)");
        data1.add("[X]Rolling Resistance Coefficient (Cᵣᵣ)");
        data1.add("[X]Roshko Number (Ro)");
        data1.add("[X]Rossby Number (Ro)");
        data1.add("[X]Rouse Number (P)");

        data1.add("[X]Schmidt Number (Sc)");
        data1.add("Sherwood Number (Sh)");
        data1.add("[X]Shields Parameter (τ*)");
        data1.add("[X]Sommerfield Number (S)");
        data1.add("[X]Stanton Number (St)");
        data1.add("[X]Stefan Number (Ste)");
        data1.add("[X]Stokes Number (Stk)");
        data1.add("[X]Strouhal Number (St)");
        data1.add("[X]Stuart Number (N)");

        data1.add("[X]Taylor Number (Ta)");

        data1.add("[X]Ursell Number (U)");

        data1.add("[X]Weber Number (We)");
        data1.add("[X]Weissenberg Number (Wi)");
        data1.add("[X]Womersley Number (α)");

        data1.add("[X]Zel'dovich Number (β)");

        adapter1.notifyDataSetChanged();

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()  {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(spinner1.getSelectedItem().toString()) {
                    case "Reynold's Number (Re)":
                        numberSel = 1;
                        resetAll();
                        hideBtn(false, true, true, false);
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{\\rho v D}{\\mu}$$</center>");
                        input_text1.setText(R.string.properties_density);
                        input_text2.setText(R.string.properties_velocity);
                        input_text3.setText(R.string.properties_lengthDiameter);
                        input_text4.setText(R.string.properties_dynamicViscosity);
                        line1Visible(); line2Visible(); line3Visible(); line4Visible();

                        if(unitSel == 0) {
                            unit1.setText(R.string.units_metric_density);
                            unit2.setText(R.string.units_metric_velocity);
                            unit3.setText(R.string.units_metric_lengthDiameter);
                            unit4.setText(R.string.units_metric_dynamicViscosity);
                        }
                        else if(unitSel == 1) {
                            unit1.setText(R.string.units_imperial_density);
                            unit2.setText(R.string.units_imperial_velocity);
                            unit3.setText(R.string.units_imperial_lengthDiameter);
                            unit4.setText(R.string.units_imperial_dynamicViscosity);
                        }
                        break;
                    case "Mach Number (Ma)":
                        numberSel = 2;
                        resetAll();
                        hideBtn(true, false, false, false);
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{u}{c}$$</center>");
                        input_text1.setText(R.string.properties_velocity);
                        unit1.setText(R.string.units_metric_velocity);
                        line1Visible();

                        if(unitSel == 0) {
                            unit1.setText(R.string.units_metric_velocity);
                        }
                        else if(unitSel == 1) {
                            unit1.setText(R.string.units_imperial_velocity);
                        }
                        break;
                    case "Atwood Number (A)":
                        numberSel = 3;
                        resetAll();
                        hideBtn(false, false, false, false);
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{\\rho_1 - \\rho_2}{\\rho_1 + \\rho_2}$$</center>");
                        input_text1.setText("Heavier Density Fluid (ρ1)");
                        input_text2.setText("Lighter Density Fluid (ρ2)");
                        unit1.setText(R.string.units_metric_density);
                        unit2.setText(R.string.units_metric_density);
                        line1Visible(); line2Visible();
                        if(unitSel == 0) {
                            unit1.setText(R.string.units_metric_density);
                            unit2.setText(R.string.units_metric_density);
                        }
                        else if(unitSel == 1) {
                            unit1.setText(R.string.units_imperial_density);
                            unit2.setText(R.string.units_imperial_density);
                        }
                        break;
                    case "Biot Number (Bi)":
                        numberSel = 4;
                        resetAll();
                        hideBtn(true, false, false, false);
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{L_C h}{k}$$<center>");
                        input_text1.setText("Characterstic Length (Lc)");
                        input_text2.setText("Heat Transfer (film) Coefficient (h)");
                        input_text3.setText("Thermal Conductivity (k)");
                        unit1.setText("m");
                        unit2.setText("W/(m²⋅K)");
                        unit3.setText("W/(m⋅K)");
                        line1Visible(); line2Visible(); line3Visible();
                        if(unitSel == 0){
                            unit1.setText("m");
                            unit2.setText("W/(m²⋅K)");
                            unit3.setText("W/(m⋅K)");
                        }
                        else if(unitSel == 1){
                            unit1.setText(R.string.units_imperial_lengthDiameter);
                            unit2.setText("BTU/(s⋅ft²⋅°F)");
                            unit3.setText("BTU/(hr⋅ft⋅°F)");
                        }
                        break;
                    case "Sherwood Number (Sh)":
                        numberSel = 5;
                        resetAll();
                        hideBtn(false, false, true, false);
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{h}{D/L}$$<center>");
                        input_text1.setText("Convective Film Coefficient (h)");
                        input_text2.setText("Mass Diffusivity (D)");
                        input_text3.setText("Characteristic Length (L)");
                        unit1.setText("m⋅s⁻¹");
                        unit2.setText("m²⋅s⁻¹");
                        unit3.setText("m");
                        line1Visible(); line2Visible(); line3Visible();
                        if(unitSel == 0){
                            unit1.setText("m⋅s⁻¹");
                            unit2.setText("m²⋅s⁻¹");
                            unit3.setText("m");
                        }
                        else if(unitSel == 1){
                            unit1.setText("ft⋅s⁻¹");
                            unit2.setText("ft²⋅s⁻¹");
                            unit3.setText("ft");
                        }
                        break;
                    case "Nusselt Number (Nu)":
                        numberSel = 6;
                        resetAll();
                        hideBtn(false, true, false, false);
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{h L}{k}$$<center>");
                        input_text1.setText("Convective Heat Transfer Coefficient (h)");
                        input_text2.setText("Characteristic Length (L)");
                        input_text3.setText("Thermal Conductivity (k)");
                        unit1.setText("W/(m²⋅K)");
                        unit2.setText("m");
                        unit3.setText("W/(m⋅K)");
                        line1Visible(); line2Visible(); line3Visible();
                        if(unitSel == 0){
                            unit1.setText("W/(m²⋅K)");
                            unit2.setText("m");
                            unit3.setText("W/(m⋅K)");
                        }
                        else if(unitSel == 1){
                            unit1.setText("BTU/(s⋅ft²⋅°F)");
                            unit2.setText("ft");
                            unit3.setText("BTU/(hr⋅ft⋅°F)");
                        }
                        break;
                    case "Prandtl Number (Pr)":
                        numberSel = 7;
                        resetAll();
                        hideBtn(false, false, false, false);
                        dimensionlessFormula.setDisplayText("<center>$$\\frac{\\nu}{\\alpha}$$<center>");
                        input_text1.setText("Kinematic Viscosity (ν)");
                        input_text2.setText("Thermal Diffusivity (α)");
                        unit1.setText("m²/s");
                        unit2.setText("m²/s");
                        line1Visible(); line2Visible();
                        if(unitSel == 0){
                            unit1.setText("m²/s");
                            unit2.setText("m²/s");
                        }
                        else if(unitSel == 1){
                            unit1.setText("ft²/s");
                            unit2.setText("ft²/s");
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
        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        button1.setAlpha(1f);
        button1.setClickable(true);
        button2.setAlpha(1f);
        button2.setClickable(true);
        button3.setAlpha(1f);
        button3.setClickable(true);
        button4.setAlpha(1f);
        button4.setClickable(true);
    }

    public void hideBtn(Boolean one, Boolean two, Boolean three, Boolean four) {
        if(one) {
            button1.setAlpha(.25f);
            button1.setClickable(false);
        }
        if(two) {
            button2.setAlpha(.25f);
            button2.setClickable(false);
        }
        if(three) {
            button3.setAlpha(.25f);
            button3.setClickable(false);
        }
        if(four) {
            button4.setAlpha(.25f);
            button4.setClickable(false);
        }
    }

    public void line1Visible() {
        input_text1.setVisibility(View.VISIBLE);
        input_field1.setVisibility(View.VISIBLE);
        unit1.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
    }
    public void line2Visible() {
        input_text2.setVisibility(View.VISIBLE);
        input_field2.setVisibility(View.VISIBLE);
        unit2.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
    }
    public void line3Visible() {
        input_text3.setVisibility(View.VISIBLE);
        input_field3.setVisibility(View.VISIBLE);
        unit3.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
    }
    public void line4Visible() {
        input_text4.setVisibility(View.VISIBLE);
        input_field4.setVisibility(View.VISIBLE);
        unit4.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
    }

    public void clearInputs() {
        input_field1.setText("");
        input_field1.setError(null);
        input_field2.setText("");
        input_field2.setError(null);
        input_field3.setText("");
        input_field3.setError(null);
        input_field4.setText("");
        input_field4.setError(null);

    }
}
