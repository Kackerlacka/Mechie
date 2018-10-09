package com.kackerlacka.mechie;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import java.util.Objects;

import katex.hourglass.in.mathlib.MathView;

public class CustomEquationsDialog extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.custom_dialog_equations, container, false);

        Bundle info = getArguments();
        String name = info.getString("KEY_TITLE");
        String equationText = info.getString("KEY_EQUATION");
        String units = info.getString("KEY_UNITS");
        String variables_one = info.getString("KEY_VARIABLES_ONE");
        String variables_two = info.getString("KEY_VARIABLES_TWO");
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_close_24);
        toolbar.setNavigationOnClickListener((View v) -> {
                dismiss();
            });
        toolbar.setTitle(name);

        MathView equation_formula = view.findViewById(R.id.formula_one);
        MathView equation_units = view.findViewById(R.id.formula_units);
        MathView formula_variables_one = view.findViewById(R.id.variables_one);
        MathView formula_variables_two = view.findViewById(R.id.variables_two);
        equation_formula.setDisplayText(equationText);
        equation_units.setDisplayText(units);
        formula_variables_one.setDisplayText(variables_one);
        formula_variables_two.setDisplayText(variables_two);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        if (dialog != null) {
            Objects.requireNonNull(dialog.getWindow()).setLayout(width, height);
        }
    }
}