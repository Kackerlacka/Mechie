package com.kackerlacka.mechie;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class ToolsSAEBoltPicker extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner1;
    private Spinner spinner2;
    List<String> data1 = new ArrayList<>();
    List<String> data2 = new ArrayList<>();
    private EditText editText;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools_saeboltpicker);

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
        getSupportActionBar().setTitle("Bolt Sizer");

        spinnerListener();
        addListenerOnButton();

    }

    // get the selected dropdown list value
    public void spinnerListener() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.sizeRange_value);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data1);

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data2);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        data1.add("1");
        data1.add("2");
        data1.add("4");
        data1.add("5");
        data1.add("5.2");
        data1.add("7");
        data1.add("8");
        data1.add("8.2");


        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()  {

            final TextView proofStrength = (TextView)findViewById(R.id.proofstrength_value);
            final TextView tensileStrength = (TextView)findViewById(R.id.tensilestrength_value);
            final TextView yieldStrength = (TextView)findViewById(R.id.yieldstrength_value);

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(spinner1.getSelectedItemPosition()) {
                    case 0:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("33 kpsi");
                        tensileStrength.setText("60 kpsi");
                        yieldStrength.setText("36 kpsi");
                        break;
                    case 1:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - ¾");
                        data2.add("⅞ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("55 kpsi");
                        tensileStrength.setText("74 kpsi");
                        yieldStrength.setText("57 kpsi");
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    proofStrength.setText("55 kpsi");
                                    tensileStrength.setText("74 kpsi");
                                    yieldStrength.setText("57 kpsi");
                                }
                                else if(spinner2.getSelectedItemPosition() == 1){
                                    proofStrength.setText("33 kpsi");
                                    tensileStrength.setText("60 kpsi");
                                    yieldStrength.setText("36 kpsi");
                                }
                            }
                        });
                        break;
                    case 2:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("65 kpsi");
                        tensileStrength.setText("115 kpsi");
                        yieldStrength.setText("100 kpsi");
                        break;
                    case 3:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1");
                        data2.add("1⅛ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("85 kpsi");
                        tensileStrength.setText("120 kpsi");
                        yieldStrength.setText("92 kpsi");
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    proofStrength.setText("85 kpsi");
                                    tensileStrength.setText("120 kpsi");
                                    yieldStrength.setText("92 kpsi");
                                }
                                else if(spinner2.getSelectedItemPosition() == 1){
                                    proofStrength.setText("74 kpsi");
                                    tensileStrength.setText("105 kpsi");
                                    yieldStrength.setText("81 kpsi");
                                }
                            }
                        });
                        break;
                    case 4:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("85 kpsi");
                        tensileStrength.setText("120 kpsi");
                        yieldStrength.setText("92 kpsi");
                        break;
                    case 5:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("105 kpsi");
                        tensileStrength.setText("133 kpsi");
                        yieldStrength.setText("115 kpsi");
                        break;
                    case 6:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("120 kpsi");
                        tensileStrength.setText("150 kpsi");
                        yieldStrength.setText("130 kpsi");
                        break;
                    case 7:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("120 kpsi");
                        tensileStrength.setText("150 kpsi");
                        yieldStrength.setText("130 kpsi");
                        break;

                }
            }

        });
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        editText = (EditText) findViewById(R.id.editText);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            TextView sizeDesignation = (TextView) findViewById(R.id.sizeDesignation_value);
            TextView majorDiameter = (TextView) findViewById(R.id.majorDiameter_value);
            TextView threads = (TextView) findViewById(R.id.threads_value);
            TextView tensileArea = (TextView) findViewById(R.id.minorarea_value);

            void setText(String size_designation, String major_diameter, String threads_,
                         String tensile_area) {
                sizeDesignation.setText(size_designation);
                majorDiameter.setText(major_diameter);
                threads.setText(threads_);
                tensileArea.setText(tensile_area);
            }

            double rootArea;

            @Override
            public void onClick(View v) {

                try {
                    rootArea = Double.parseDouble(editText.getText().toString());
                } catch ( Exception e) {
                    Log.e("InvalidNumber","Can not parse zero string");
                    return; // Or another exception handling.
                }

                if (rootArea == 0) {
                    setText("0", "0.0600", "-", "-");
                }
                else if (rootArea <= 0.00218 && rootArea > 0) {
                    setText("1", "0.0730", "64", "0.00263");
                }
                else if(rootArea > 0.000218 && rootArea <= 0.00310){
                    setText("2", "0.0860", "56", "0.00370");
                }
                else if(rootArea > 0.00310 && rootArea <= 0.00406){
                    setText("3", "0.0990", "48", "0.00487");
                }
                else if(rootArea > 0.00406 && rootArea <= 0.00496){
                    setText("4", "0.1120", "40", "0.00604");
                }

                else if(rootArea > 0.00496 && rootArea <= 0.00672){
                    setText("5", "0.1250", "40", "0.00796");
                }
                else if(rootArea > 0.00672 && rootArea <= 0.00745){
                    setText("6", "0.1380", "32", "0.00909");
                }
                else if(rootArea > 0.00745 && rootArea <= 0.01196){
                    setText("8", "0.1640", "32", "0.0140");
                }
                else if(rootArea > 0.01196 && rootArea <= 0.01450){
                    setText("10", "0.1900", "24", "0.0175");
                }
                else if(rootArea > 0.01450 && rootArea <= 0.0206){
                    setText("12", "0.2160", "24", "0.0242");
                }
                else if(rootArea > 0.0206 && rootArea <= 0.0269) {
                    setText("¼", "0.2500", "20", "0.0318");
                }
                else if(rootArea > 0.0269 && rootArea <= 0.0454){
                    setText("⁵/₁₆", "0.3125", "18", "0.0524");
                }
                else if(rootArea > 0.0454 && rootArea <= 0.0678){
                    setText("⅜", "0.3750", "16", "0.0775");
                }
                else if(rootArea > 0.0678 && rootArea <= 0.0933){
                    setText("⁷/₁₆", "0.4375", "14", "0.1063");
                }
                else if(rootArea > 0.0933 && rootArea <= 0.1257){
                    setText("½", "0.5000", "13", "0.1419");
                }
                else if(rootArea > 0.1257 && rootArea <= 0.162){
                    setText("⁹/₁₆", "0.5625", "12", "0.182");
                }
                else if(rootArea > 0.162 && rootArea <= 0.202){
                    setText("⅝", "0.6250", "11", "0.226");
                }
                else if(rootArea > 0.202 && rootArea <= 0.302){
                    setText("¾", "0.7500", "10", "0.334");
                }
                else if(rootArea > 0.302 && rootArea <= 0.419){
                    setText("⅞", "0.8750", "9", "0.462");
                }
                else if(rootArea > 0.419 && rootArea <= 0.551){
                    setText("1", "1.000", "8", "0.606");
                }
                else if(rootArea > 0.551 && rootArea <= 0.890){
                    setText("1¼", "1.2500", "7", "0.969");
                }
                else if(rootArea > 0.890 && rootArea <= 1.294){
                    setText("1½", "1.5000", "6", "1.405");
                }
                else if(rootArea > 1.294){
                    editText.setError("Input must be 1.294 or less");
                }

            }
        });
    }
}
