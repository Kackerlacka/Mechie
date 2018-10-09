package com.kackerlacka.mechie;

import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import katex.hourglass.in.mathlib.MathView;

public class MechanicsCastiglianosTheorem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanics_castiglianostheorem);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Castigliano's Method");

        MathView formula1 = findViewById(R.id.formula1);
        MathView formula2 = findViewById(R.id.formula2);
        MathView formula3 = findViewById(R.id.formula3);
        MathView formula4 = findViewById(R.id.formula4);
        MathView formula5 = findViewById(R.id.formula5);

        formula2.setTextSize(14);
        formula3.setTextSize(14);
        formula4.setTextSize(14);
        formula5.setTextSize(14);

        formula1.setDisplayText("<center>$$U = W_i$$</center>");
        formula2.setDisplayText("<center>$$\\delta = \\frac{\\partial U}{\\partial P}~~~~~~\\theta = \\frac{\\partial U}{\\partial \\bar{M}}$$</center>");
        formula3.setDisplayText("<center>$$\\delta = \\frac{\\partial U}{\\partial P}$$</center>");
        formula4.setDisplayText("<center>$$\\delta = \\frac{\\partial}{\\partial P} \\int_{0}^{L} \\frac{M^2(x)}{2EI}dx$$</center>");
        formula5.setDisplayText("<center>$$\\delta = \\frac{\\partial}{\\partial P} \\int_{0}^{L}\\frac{(Px)^2}{2EI}dx$$</center>");
    }
}
