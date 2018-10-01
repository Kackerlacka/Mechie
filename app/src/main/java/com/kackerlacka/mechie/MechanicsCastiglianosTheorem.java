package com.kackerlacka.mechie;

import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.github.kexanie.library.MathView;

public class MechanicsCastiglianosTheorem extends AppCompatActivity {

    private Toolbar toolbar;
    private MathView formula1;
    private MathView formula2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanics_castiglianostheorem);

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
        getSupportActionBar().setTitle("Castigliano's Method");

        formula1 = findViewById(R.id.formula1);
        formula2 = findViewById(R.id.formula2);

        formula1.setText("<center>$$U = W_i$$</center>");
        formula2.setText("<center>$$\\delta = \\frac{\\partial U}{\\partial P}~or~\\theta = \\frac{\\partial U}{\\partial \\bar{M}}");
    }
}
