package com.kackerlacka.mechie;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;

public class SpringDesigner extends AppCompatActivity {

    private Spinner wireMaterialSpinner;
    private Spinner noSpringsSpinner;
    private Spinner springEndsSpinner;
    private EditText input_wireDiameter, input_springIndex, input_extForce, input_springDeflection,
        input_loadings1, input_loadings2, input_power1, input_power2, input_speed1, input_speed2;
    private TextView ksValue, tauStaticValue, DValue, SsyValue, SutValue, FSStaticValue;
    private TextView kbValue, tauAValue, tauMValue, SsuValue, SsaValue, SsmValue, SseValue, FSFatigueValue;
    private TextView EModulus, ShearModulus, NtValue, NaValue, KValue;
    List<String> wireMaterial = new ArrayList<>();
    List<String> noSprings = new ArrayList<>();
    List<String> springEnds = new ArrayList<>();
    private RadioGroup unitsRadioGroup;
    private RadioGroup peenedRadioGroup;
    private int unitSel = 1;
    Context context = this;
    DecimalFormat formatter;
    double ssa = 0, ssm = 0, C = 0;
    private int scrollValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spring_designer);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Helical Spring Designer");

        initializeActivity();

        RadioButton rb1 = findViewById(R.id.peened);
        rb1.setChecked(true);

        unitsRadioGroup = findViewById(R.id.units_radioGroup);
        unitsRadioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
            switch(checkedId) {
                case R.id.metric:
                    clearInputs();
                    input_wireDiameter.setHint("mm");
                    input_extForce.setHint("N");
                    input_springDeflection.setHint("mm");
                    input_loadings1.setHint("N");
                    input_loadings2.setHint("N");
                    input_power1.setHint("W");
                    input_power2.setHint("W");
                    ssa = 398;
                    ssm = 534;
                    unitSel = 1;
                    break;
                case R.id.imperial:
                    clearInputs();
                    input_wireDiameter.setHint("in");
                    input_extForce.setHint("lb");
                    input_springDeflection.setHint("in");
                    input_loadings1.setHint("lb");
                    input_loadings2.setHint("lb");
                    input_power1.setHint("hp");
                    input_power2.setHint("hp");
                    ssa = 57.5;
                    ssm = 77.5;
                    unitSel = 2;
                    break;
            }
        });

        peenedRadioGroup = findViewById(R.id.peened_group);
        peenedRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.peened:
                        if(unitSel == 1) {
                            ssa = 398;
                            ssm = 534;
                        }
                        else if(unitSel == 2) {
                            ssa = 57.5;
                            ssm = 77.5;
                        }
                        break;
                    case R.id.unpeened:
                        if(unitSel == 1) {
                            ssa = 241;
                            ssm = 379;
                        }
                        else if(unitSel == 2) {
                            ssa = 35;
                            ssm = 55;
                        }
                        break;
                }
            }
        });


        final Button calculateButton = findViewById(R.id.btn_calculate);
        calculateButton.setOnClickListener((View v) -> {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

            calculateKs();
            calculateD();
            calculateForce();
            calculateTauStatic();
            calculateSsy();
            calculateFSStatic();

            calculateKb();
            calculateTauA();
            calculateTauM();
            calculateSsu();
            calculateSse();

            calculateNa();
            calculateNt();

            SsaValue = findViewById(R.id.ssa_value);
            SsmValue = findViewById(R.id.ssm_value);
            if(unitSel == 1) {
                SsaValue.setText(formatter.format(ssa) + " MPa");
                SsmValue.setText(formatter.format(ssm) + " MPa");
            }
            else if(unitSel == 2) {
                SsaValue.setText(formatter.format(ssa) + " kspi");
                SsmValue.setText(formatter.format(ssm) + " kspi");
            }

            calculateFSFatigue();

            if(!input_wireDiameter.getText().toString().equals("") && scrollValue == 1 &&
                    !input_springIndex.getText().toString().equals("") && !input_extForce.getText().toString().equals("")
                    && !input_springDeflection.getText().toString().equals("") && !input_loadings1.getText().toString().equals("")
                    && !input_loadings2.equals("") && !input_power1.equals("") && !input_power2.equals("") &&
                    !input_speed1.getText().toString().equals("") && !input_speed2.getText().toString().equals("")) {
                ScrollView sv = findViewById(R.id.scrollview);
                sv.smoothScrollTo(0, sv.getBottom());

            }
        });

    }


    public double calculateKs() {
        ksValue = findViewById(R.id.ks_value);
        double ks = 0;

        try {
            String CValue = input_springIndex.getText().toString();
            C = Double.parseDouble(CValue);
        } catch (NumberFormatException e) {
            input_springIndex.setError("Enter a spring index");
        }

        if(C >=4 && C <= 12) {
            ks = (2*C + 1)/(2*C);
            ksValue.setText(formatter.format(ks));
        }
        else {
            input_springIndex.setError("Number must be between 4 and 12");
        }

        return ks;
    }

    public double calculateTauStatic() {
        tauStaticValue = findViewById(R.id.tau_static_value);
        double diameter = 0;
        double ksValue = calculateKs();
        double extForce = calculateForce();
        double D = calculateD();

        try {
            String diameterValue = input_wireDiameter.getText().toString();
            diameter = Double.parseDouble(diameterValue);
        } catch (NumberFormatException e) {
            input_wireDiameter.setError("Enter a wire diameter");
        }


        double tauStatic = (ksValue * 8 * extForce * D)/(PI * (diameter*diameter*diameter));
        if(unitSel == 1) {
            tauStaticValue.setText(formatter.format(tauStatic) + " MPa");
        }
        else if(unitSel == 2) {
            tauStaticValue.setText(formatter.format(tauStatic) + " kpsi");
        }
        return tauStatic;
    }

    public double calculateD() {
        DValue = findViewById(R.id.D_value);
        double diameter = 0;
        double C = 0;

        try {
            String CValue = input_springIndex.getText().toString();
            C = Double.parseDouble(CValue);
        } catch (NumberFormatException e) {
            input_springIndex.setError("Enter a spring index");
        }

        try {
            String diameterValue = input_wireDiameter.getText().toString();
            diameter = Double.parseDouble(diameterValue);
        } catch (NumberFormatException e) {
            input_wireDiameter.setError("Enter a wire diameter");
        }

        double D = C*diameter;
        DValue.setText(formatter.format(D));

        return D;
    }

    public double calculateForce() {
        double extForce = 0;
        double extForceFinal = 0;

        try {
            String extForceValue = input_extForce.getText().toString();
            extForce = Double.parseDouble(extForceValue);
        } catch (NumberFormatException e) {
            input_extForce.setError("Enter an external force value");
        }

        switch(noSpringsSpinner.getSelectedItemPosition()) {
            case 0:
                extForceFinal = (extForce)/1000;
                break;
            case 1:
                extForceFinal = (extForce/2)/1000;
                break;
            case 2:
                extForceFinal = (extForce/3)/1000;
                break;
            case 3:
                extForceFinal = (extForce/4)/1000;
                break;
            case 4:
                extForceFinal = (extForce/5)/1000;
                break;
            case 5:
                extForceFinal = (extForce/6)/1000;
                break;
            case 6:
                extForceFinal = (extForce/7)/1000;
                break;
            case 7:
                extForceFinal = (extForce/8)/1000;
                break;
        }

        return extForceFinal;
    }

    public double calculateSut() {
        SutValue = findViewById(R.id.sut_value);
        double diameter = 0;
        double m = 0;
        double A = 0;
        double sut = 0;

        try {
            String diameterValue = input_wireDiameter.getText().toString();
            diameter = Double.parseDouble(diameterValue);
        } catch (NumberFormatException e) {
            input_wireDiameter.setError("Enter a wire diameter");
        }

        switch(wireMaterialSpinner.getSelectedItemPosition()) {
            case 0:
                if(unitSel == 1) {
                    if(diameter >= 0.10 && diameter <= 6.5 && !input_wireDiameter.equals("")) {
                        m = 0.145;
                        A = 2211;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.10 && diameter != 0 || diameter > 6.5){
                        input_wireDiameter.setError("Diameter must be between 0.10 mm and 6.5 mm for Music Wire A228");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                else if(unitSel == 2) {
                    if(diameter >= 0.004 && diameter <= 0.256) {
                        m = 0.145;
                        A = 201;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.004 && diameter != 0 || diameter > 0.256){
                        input_wireDiameter.setError("Diameter must be between 0.004 in and 0.256 in for Music Wire A228");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                break;
            case 1:
                if(unitSel == 1) {
                    if(diameter >= 0.5 && diameter <= 12.7) {
                        m = 0.187;
                        A = 1855;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.5 && diameter != 0 || diameter > 12.7) {
                        input_wireDiameter.setError("Diameter must be between 0.5 mm and 12.7 mm for OQ&T Wire A229");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                else if(unitSel == 2) {
                    if(diameter >= 0.020 && diameter <= 0.500) {
                        m = 0.187;
                        A = 147;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.020 && diameter != 0 || diameter > 0.500) {
                        input_wireDiameter.setError("Diameter must be between 0.020 in and 0.500 in for OQ&T Wire A229");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                break;
            case 2:
                if(unitSel == 1) {
                    if(diameter >= 0.7 && diameter <= 12.7) {
                        m = 0.190;
                        A = 1783;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.7 && diameter != 0 || diameter > 12.7) {
                        input_wireDiameter.setError("Diameter must be between 0.7 mm and 12.7 mm for Hard-Drawn Wire A227");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                else if(unitSel == 2) {
                    if(diameter >= 0.028 && diameter <= 0.500) {
                        m = 0.190;
                        A = 140;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.028 && diameter != 0 || diameter > 0.500){
                        input_wireDiameter.setError("Diameter must be between 0.028 in and 0.500 in for Hard-Drawn Wire A227");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                break;
            case 3:
                if(unitSel == 1) {
                    if(diameter >= 0.8 && diameter <= 11.1) {
                        m = 0.168;
                        A = 2005;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.8 && diameter != 0 || diameter > 11.1) {
                        input_wireDiameter.setError("Diameter must be between 0.8 mm and 11.1 mm for Chrome-Vanadium Wire A232");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                else if(unitSel == 2) {
                    if(diameter >= 0.032 && diameter <= 0.437) {
                        m = 0.168;
                        A = 169;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.032 && diameter != 0 || diameter > 0.437) {
                        input_wireDiameter.setError("Diameter must be between 0.032 in and 0.437 in for Chrome-Vanadium Wire A232");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                break;
            case 4:
                if(unitSel == 1) {
                    if(diameter >= 1.6 && diameter <= 9.5) {
                        m = 0.108;
                        A = 1974;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 1.6 && diameter != 0 || diameter > 9.5) {
                        input_wireDiameter.setError("Diameter must be between 1.6 mm and 9.5 mm for Chrome-Silicon Wire A401");
                        scrollValue = 0;
                    }
                    else {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                else if(unitSel == 2) {
                    if(diameter >= 0.063 && diameter <= 0.375) {
                        m = 0.108;
                        A = 202;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.063 && diameter != 0 || diameter > 0.375) {
                        input_wireDiameter.setError("Diameter must be between 0.063 in and 0.375 in for Chrome-Silicon Wire A401");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                break;
            case 5:
                if(unitSel == 1) {
                    if(diameter >= 0.3 && diameter < 2.5) {
                        m = 0.146;
                        A = 1876;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter >= 2.5 && diameter < 5) {
                        m = 0.263;
                        A = 2065;
                        sut = A/(Math.pow(diameter, m));
                        SutValue.setText(formatter.format(sut));
                    }
                    else if(diameter >= 5 && diameter <= 10) {
                        m = 0.478;
                        A = 2911;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.3 && diameter != 0 || diameter > 10) {
                        input_wireDiameter.setError("Diameter must be between 0.3 mm and 10 mm for 302 Stainless Wire A313");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                else if(unitSel == 2) {
                    if(diameter >= 0.013 && diameter < 0.10) {
                        m = 0.146;
                        A = 169;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter >= 0.10 && diameter < 0.20) {
                        m = 0.263;
                        A = 128;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter >= 0.20 && diameter <= 0.40) {
                        m = 0.478;
                        A = 90;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.013 && diameter != 0 || diameter > 0.40) {
                        input_wireDiameter.setError("Diameter must be between 0.013 in and 0.40 in for 302 Stainless Wire A313");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                break;
            case 6:
                if(unitSel == 1) {
                    if(diameter >= 0.1 && diameter < 0.6) {
                        m = 0;
                        A = 1000;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter >= 0.6 && diameter < 2) {
                        m = 0.028;
                        A = 913;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter >= 2 && diameter <= 7.5) {
                        m = 0.064;
                        A = 932;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.1 && diameter != 0 || diameter > 7.5){
                        input_wireDiameter.setError("Diameter must be between 0.1 mm and 7.5 mm for Phosphor-Bronze Wire B159");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                else if(unitSel == 2) {
                    if(diameter >= 0.004 && diameter < 0.022) {
                        m = 0;
                        A = 145;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter >= 0.022 && diameter < 0.075) {
                        m = 0.028;
                        A = 121;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter >= 0.075 && diameter <= 0.30) {
                        m = 0.064;
                        A = 110;
                        sut = A/(Math.pow(diameter, m));
                        if(unitSel == 1) {
                            SutValue.setText(formatter.format(sut) + " MPa");
                            scrollValue = 1;
                        }
                        else if(unitSel == 2) {
                            SutValue.setText(formatter.format(sut) + " kpsi");
                            scrollValue = 1;
                        }
                    }
                    else if(diameter < 0.004 && diameter != 0 || diameter > 0.30) {
                        input_wireDiameter.setError("Diameter must be between 0.004 in and 0.022 in for Phosphor-Bronze Wire B159");
                        scrollValue = 0;
                    }
                    else  {
                        input_wireDiameter.setError("Enter a wire diameter");
                        scrollValue = 0;
                    }
                }
                break;
        }

        return sut;
    }

    public double calculateSsy() {
        SsyValue = findViewById(R.id.ssy_value);
        double sut = calculateSut();

        double ssy = 0.45*sut;
        if(unitSel == 1) {
            SsyValue.setText(formatter.format(ssy) + " MPa");
        }
        else if(unitSel == 2) {
            SsyValue.setText(formatter.format(ssy) + " kpsi");
        }
        return ssy;
    }

    public double calculateFSStatic() {
        FSStaticValue = findViewById(R.id.fs_static_value);
        double ssy = calculateSsy();
        double tau = calculateTauStatic();

        double fsStatic = ssy/tau;
        FSStaticValue.setText(formatter.format(fsStatic));
        return fsStatic;
    }

    public double calculateKb() {
        kbValue = findViewById(R.id.kb_value);
        double C = 0;

        try {
            String CValue = input_springIndex.getText().toString();
            C = Double.parseDouble(CValue);
        } catch (NumberFormatException e) {
            input_springIndex.setError("Enter a spring index");
        }

        double kb = (4*C + 2)/(4*C - 3);
        kbValue.setText(formatter.format(kb));

        return kb;
    }

    public double calculateTauA() {
        tauAValue = findViewById(R.id.taua_value);
        double diameter = 0;
        double kbValue = calculateKb();
        double extForceA = calculateForceA();
        double D = calculateD();

        try {
            String diameterValue = input_wireDiameter.getText().toString();
            diameter = Double.parseDouble(diameterValue);
        } catch (NumberFormatException e) {
            input_wireDiameter.setError("Enter a wire diameter");
        }


        double tauA = (kbValue * 8 * extForceA * D)/(PI * (diameter*diameter*diameter));
        if(unitSel == 1) {
            tauAValue.setText(formatter.format(tauA) + " MPa");
        }
        else if(unitSel == 2) {
            tauAValue.setText(formatter.format(tauA) + " kpsi");
        }
        return tauA;
    }

    public double calculateTauM() {
        tauMValue = findViewById(R.id.taum_value);
        double diameter = 0;
        double kbValue = calculateKb();
        double extForceM = calculateForceM();
        double D = calculateD();

        try {
            String diameterValue = input_wireDiameter.getText().toString();
            diameter = Double.parseDouble(diameterValue);
        } catch (NumberFormatException e) {
            input_wireDiameter.setError("Enter a wire diameter");
        }


        double tauM = (kbValue * 8 * extForceM * D)/(PI * (diameter*diameter*diameter));
        if(unitSel == 1) {
            tauMValue.setText(formatter.format(tauM) + " MPa");
        }
        else if(unitSel == 2) {
            tauMValue.setText(formatter.format(tauM) + " kpsi");
        }
        return tauM;
    }

    public double calculateForceA() {
        double Fmax = calculateFmax();
        double Fmin = calculateFmin();
        double extForceA;

        extForceA = ((Fmax/1000) - (Fmin/1000))/2;

        return extForceA;
    }

    public double calculateForceM() {
        double Fmax = calculateFmax();
        double Fmin = calculateFmin();
        double extForceM;

        extForceM = ((Fmax/1000) + (Fmin/1000))/2;

        return extForceM;
    }

    public double calculateFmax() {
        double extLoad2 = 0;
        double extForce = 0;

        try {
            String extLoadings2 = input_loadings2.getText().toString();
            extLoad2 = Double.parseDouble(extLoadings2);
        } catch (NumberFormatException e) {
            input_loadings2.setError("Enter a loading value");
        }

        double numberSprings = Double.parseDouble(noSpringsSpinner.getSelectedItem().toString());

        try {
            String extForceValue = input_extForce.getText().toString();
            extForce = Double.parseDouble(extForceValue);
        } catch (NumberFormatException e) {
            input_extForce.setError("Enter a force value");
        }

        double Fmax = (extForce/numberSprings) + (extLoad2/numberSprings);

        return Fmax;
    }

    public double calculateFmin() {
        double extLoad1 = 0;
        double extForce = 0;

        try {
            String extLoadings1 = input_loadings1.getText().toString();
            extLoad1 = Double.parseDouble(extLoadings1);
        } catch (NumberFormatException e) {
            input_loadings1.setError("Enter a loading value");
        }

        double numberSprings = Double.parseDouble(noSpringsSpinner.getSelectedItem().toString());

        try {
            String extForceValue = input_extForce.getText().toString();
            extForce = Double.parseDouble(extForceValue);
        } catch (NumberFormatException e) {
            input_extForce.setError("Enter a force");
        }

        double Fmin = (extForce/numberSprings) + (extLoad1/numberSprings);

        return Fmin;
    }

    public double calculateSsu() {
        SsuValue = findViewById(R.id.ssu_value);
        double sut = calculateSut();

        double ssu = 0.67*sut;
        if(unitSel == 1) {
            SsuValue.setText(formatter.format(ssu) + " MPa");
        }
        else if(unitSel == 2) {
            SsuValue.setText(formatter.format(ssu) + " kpsi");
        }
        return ssu;
    }

    public double calculateSse() {
        SseValue = findViewById(R.id.sse_value);
        double ssu = calculateSsu();

        double sse = ssa/(1 - Math.pow((ssm/ssu), 2));
        if(unitSel == 1) {
            SseValue.setText(formatter.format(sse) + " MPa");
        }
        else if(unitSel == 2) {
            SseValue.setText(formatter.format(sse) + " kpsi");
        }
        return sse;
    }

    public double calculateFSFatigue() {
        FSFatigueValue = findViewById(R.id.fs_fatigue_value);
        double tauA = calculateTauA();
        double tauM = calculateTauM();
        double Sse = calculateSse();
        double Ssu = calculateSsu();

        double fsFatigue = 1/((tauA/Sse) + (tauM/Ssu));
        FSFatigueValue.setText(formatter.format(fsFatigue));
        return fsFatigue;
    }

    public double calculateK() {
        double force = calculateFmax();
        double deflection = 0;
        double k = 0;
        KValue = findViewById(R.id.spring_rate);

        try {
            String displacement = input_springDeflection.getText().toString();
            deflection = Double.parseDouble(displacement);
        } catch (NumberFormatException e) {
            input_springDeflection.setError("Enter a deflection");
        }

        k = force/deflection;

        KValue.setText(formatter.format(k));
        return k;
    }

    public double calculateNa() {
        NaValue = findViewById(R.id.active_coils);
        double dia = 0;

        try {
            String diameter = input_wireDiameter.getText().toString();
            dia = Double.parseDouble(diameter);
        } catch (NumberFormatException e) {
            input_wireDiameter.setError("Enter a wire diameter");
        }

        double G = 0;
        double D = calculateD();
        double k = calculateK();

        switch(wireMaterialSpinner.getSelectedItemPosition()) {
            case 0:
                if(unitSel == 1) {
                    if(dia < 0.8128) {
                        G = 82700000;
                    }
                    else if(dia >= 0.8382 && dia <= 1.6002) {
                        G = 81700000;
                    }
                    else if(dia >= 1.6256 && dia <= 3.175) {
                        G = 81000000;
                    }
                    else if(dia > 3.175) {
                        G = 80000000;
                    }
                }
                else if(unitSel == 2){
                    if(dia < 0.032) {
                        G = 12000000;
                    }
                    else if(dia >= 0.033 && dia <= 0.063) {
                        G = 11850000;
                    }
                    else if(dia >= 0.064 && dia <= 0.125) {
                        G = 11750000;
                    }
                    else if(dia > 0.125) {
                        G = 11600000;
                    }
                }
                break;
            case 1:
                if(unitSel == 1) {
                    G = 77200000;
                }
                else if(unitSel == 2) {
                    G = 11200000;
                }
                break;
            case 2:
                if(unitSel == 1) {
                    if(dia < 0.8128) {
                        G = 80700000;
                    }
                    else if(dia >= 0.8382 && dia <= 1.6002) {
                        G = 80000000;
                    }
                    else if(dia >= 1.6256 && dia <= 3.175) {
                        G = 79300000;
                    }
                    else if(dia > 3.175) {
                        G = 78600000;
                    }
                }
                else if(unitSel == 2){
                    if(dia < 0.032) {
                        G = 11700000;
                    }
                    else if(dia >= 0.033 && dia <= 0.063) {
                        G = 11600000;
                    }
                    else if(dia >= 0.064 && dia <= 0.125) {
                        G = 11500000;
                    }
                    else if(dia > 0.125) {
                        G = 11400000;
                    }
                }
                break;
            case 3:
                if(unitSel == 1) {
                    G = 77200000;
                }
                else if(unitSel == 2) {
                    G = 11200000;
                }
                break;
            case 4:
                if(unitSel == 1) {
                    G = 77200000;
                }
                else if(unitSel == 2) {
                    G = 11200000;
                }
                break;
            case 5:
                if(unitSel == 1) {
                    G = 69000000;
                }
                else if(unitSel == 2) {
                    G = 10000000;
                }
                break;
            case 6:
                if(unitSel == 1) {
                    G = 41400000;
                }
                else if(unitSel == 2) {
                    G = 6000000;
                }
                break;
        }

        double Na = ((dia*dia*dia*dia) * G) / (8 * (D*D*D) * k);
        NaValue.setText(formatter.format(Na));

        return Na;
    }

    public double calculateNt() {
        NtValue = findViewById(R.id.total_coils);
        double Na = calculateNa();
        double Nt = 0;

        switch(springEndsSpinner.getSelectedItemPosition()) {
            case 0:
                Nt = Na;
                break;
            case 1:
                Nt = Na + 1;
                break;
            case 2:
                Nt = Na + 2;
                break;
            case 3:
                Nt = Na + 2;
                break;
        }

        NtValue.setText(formatter.format(Nt));
        return Nt;
    }

    public void initializeActivity() {
        input_wireDiameter = findViewById(R.id.input_wireDiameter);
        input_springIndex = findViewById(R.id.input_springIndex);
        input_extForce = findViewById(R.id.input_extForce);
        input_springDeflection = findViewById(R.id.input_springDeflection);
        input_loadings1 = findViewById(R.id.input_loadings1);
        input_loadings2 = findViewById(R.id.input_loadings2);
        input_power1 = findViewById(R.id.input_power1);
        input_power2 = findViewById(R.id.input_power2);
        input_speed1 = findViewById(R.id.input_speed1);
        input_speed2 = findViewById(R.id.input_speed2);
        peenedRadioGroup = findViewById(R.id.peened_group);
        EModulus = findViewById(R.id.elasticity_value);
        ShearModulus = findViewById(R.id.shear_modulus_value);
        formatter = new DecimalFormat("#.###");


        wireMaterialSpinner = findViewById(R.id.spinner_wireMaterial);
        final ArrayAdapter<String> wireMaterialAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, wireMaterial);
        wireMaterialSpinner.setAdapter(wireMaterialAdapter);
        wireMaterial.add("Music Wire A228");
        wireMaterial.add("OQ&T Wire A229");
        wireMaterial.add("Hard-drawn Wire A227");
        wireMaterial.add("Chrome-Vanadium Wire A232");
        wireMaterial.add("Chrome-Silicon Wire A401");
        wireMaterial.add("302 Stainless Wire A313");
        wireMaterial.add("Phosphor-Bronze Wire B159");
        wireMaterialAdapter.notifyDataSetChanged();

        noSpringsSpinner = findViewById(R.id.spinner_noSprings);
        final ArrayAdapter<String> noSpringsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, noSprings);
        noSpringsSpinner.setAdapter(noSpringsAdapter);
        noSprings.add("1");
        noSprings.add("2");
        noSprings.add("3");
        noSprings.add("4");
        noSprings.add("5");
        noSprings.add("6");
        noSprings.add("7");
        noSprings.add("8");
        noSpringsAdapter.notifyDataSetChanged();

        springEndsSpinner = findViewById(R.id.spinner_springEnds);
        final ArrayAdapter<String> springEndsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, springEnds);
        springEndsSpinner.setAdapter(springEndsAdapter);
        springEnds.add("Plain");
        springEnds.add("Plain and Ground");
        springEnds.add("Squared or Closed");
        springEnds.add("Squared and Ground");
        springEndsAdapter.notifyDataSetChanged();

        Button clearBtn = findViewById(R.id.btn_clear);
        clearBtn.setOnClickListener((View v) -> {
            clearInputs();
        });

    }

    public void clearInputs() {
        input_wireDiameter.setText("");
        input_wireDiameter.setError(null);
        input_springIndex.setText("");
        input_springIndex.setError(null);
        input_extForce.setText("");
        input_extForce.setError(null);
        input_springDeflection.setText("");
        input_springDeflection.setError(null);
        input_loadings1.setText("");
        input_loadings1.setError(null);
        input_loadings2.setText("");
        input_loadings2.setError(null);
        input_power1.setText("");
        input_power1.setError(null);
        input_power2.setText("");
        input_power2.setError(null);
        input_speed1.setText("");
        input_speed1.setError(null);
        input_speed2.setText("");
        input_speed2.setError(null);
    }
}
