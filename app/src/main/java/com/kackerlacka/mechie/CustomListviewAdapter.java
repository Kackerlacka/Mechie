package com.kackerlacka.mechie;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomListviewAdapter extends MatchableArrayAdapter<CustomListViewClass> {

    public CustomListviewAdapter(Context ctx, List<CustomListViewClass> list) {
        super(ctx, R.layout.row_layout, list);
    }

    @Override
    protected void onBind(CustomListViewClass item, View itemView, int position) {
        TextView text1 = (TextView) itemView.findViewById(R.id.textView);
        text1.setText(item.getmName());
        TextView text2 = (TextView) itemView.findViewById(R.id.formula_one);
        text2.setText(item.getmEquation());
    }
    @Override
    protected boolean matches(CustomListViewClass value, CharSequence prefix, CharSequence lowerCasePrefix) {
        return value.getmName().toLowerCase().contains(lowerCasePrefix);
    }
}