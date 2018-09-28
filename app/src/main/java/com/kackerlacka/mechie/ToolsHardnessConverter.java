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

public class ToolsHardnessConverter extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner1;
    List<String> data1 = new ArrayList<>();

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
        data1.add("Leeb RB");


        adapter1.notifyDataSetChanged();

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener()  {

            final TextView brinell = (TextView)findViewById(R.id.brinell_value);
            final TextView vickers = (TextView)findViewById(R.id.vickers_value);
            final TextView rockwellA = (TextView)findViewById(R.id.rwa_value);

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(spinner1.getSelectedItemPosition()) {
                    case 0:
                        brinell.setText("33");
                        vickers.setText("60");
                        rockwellA.setText("36");
                        break;
                    case 1:
                        brinell.setText("55");
                        vickers.setText("74");
                        rockwellA.setText("57");
                        break;
                    case 2:
                        brinell.setText("65");
                        vickers.setText("115");
                        rockwellA.setText("100");
                        break;
                    case 3:
                        brinell.setText("85");
                        vickers.setText("120");
                        rockwellA.setText("92");
                        break;
                    case 4:
                        brinell.setText("85");
                        vickers.setText("120");
                        rockwellA.setText("92");
                        break;
                    case 5:
                        brinell.setText("105");
                        vickers.setText("133");
                        rockwellA.setText("115");

                }
            }

        });
    }
}
