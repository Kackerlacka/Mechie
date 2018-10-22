package com.kackerlacka.mechie;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import katex.hourglass.in.mathlib.MathView;

public class MechanicsMomentofInertia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanics_momentofinertia);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Moment of Inertia");

        MathView formula1 = findViewById(R.id.formula1);
        MathView formula2 = findViewById(R.id.formula2);
        MathView formula3 = findViewById(R.id.formula3);
        MathView formula4 = findViewById(R.id.formula4);

        formula1.setTextSize(14);
        formula2.setTextSize(14);
        formula3.setTextSize(14);
        formula4.setTextSize(14);

        formula1.setDisplayText("<center>$$I = \\frac{L}{\\omega}$$</center>");
        formula2.setDisplayText("<center>$$\\tau = I \\alpha $$</center>");
        formula3.setDisplayText("<center>$$I = m r^2$$</center>");
        formula4.setDisplayText("<center>$$I = m k^2$$</center>");

    }
}
