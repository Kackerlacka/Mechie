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

public class ToolsDimensionlessNumbersCalc extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner1;
    private Spinner spinner2;
    List<String> data1 = new ArrayList<>();
    List<String> data2 = new ArrayList<>();

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

        addListenerOnButton();

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

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
                        proofStrength.setText("33");
                        tensileStrength.setText("60");
                        yieldStrength.setText("36");
                        break;
                    case 1:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - ¾");
                        data2.add("⅞ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("55");
                        tensileStrength.setText("74");
                        yieldStrength.setText("57");
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    proofStrength.setText("55");
                                    tensileStrength.setText("74");
                                    yieldStrength.setText("57");
                                }
                                else if(spinner2.getSelectedItemPosition() == 1){
                                    proofStrength.setText("33");
                                    tensileStrength.setText("60");
                                    yieldStrength.setText("36");
                                }
                            }
                        });
                        break;
                    case 2:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("65");
                        tensileStrength.setText("115");
                        yieldStrength.setText("100");
                        break;
                    case 3:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1");
                        data2.add("1⅛ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("85");
                        tensileStrength.setText("120");
                        yieldStrength.setText("92");
                        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spinner2.getSelectedItemPosition() == 0) {
                                    proofStrength.setText("85");
                                    tensileStrength.setText("120");
                                    yieldStrength.setText("92");
                                }
                                else if(spinner2.getSelectedItemPosition() == 1){
                                    proofStrength.setText("74");
                                    tensileStrength.setText("105");
                                    yieldStrength.setText("81");
                                }
                            }
                        });
                        break;
                    case 4:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("85");
                        tensileStrength.setText("120");
                        yieldStrength.setText("92");
                        break;
                    case 5:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("105");
                        tensileStrength.setText("133");
                        yieldStrength.setText("115");
                        break;
                    case 6:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1½");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("120");
                        tensileStrength.setText("150");
                        yieldStrength.setText("130");
                        break;
                    case 7:
                        spinner2.setSelection(0);
                        data2.clear();
                        data2.add("¼ - 1");
                        adapter2.notifyDataSetChanged();
                        proofStrength.setText("120");
                        tensileStrength.setText("150");
                        yieldStrength.setText("130");
                        break;

                }
            }

        });
    }
}
