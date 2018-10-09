package com.kackerlacka.mechie;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import java.util.List;

public class CustomListviewAdapter extends MatchableArrayAdapter<CustomListViewClass> {

    CustomListviewAdapter(Context ctx, List<CustomListViewClass> list) {
        super(ctx, R.layout.row_layout, list);
    }

    @Override
    protected void onBind(CustomListViewClass item, View itemView, int position) {
        TextView text1 = itemView.findViewById(R.id.textView);
        text1.setText(item.getmName());
        TextView text2 = itemView.findViewById(R.id.formula_one);
        text2.setText(item.getmEquation());
        TextView text3 = itemView.findViewById(R.id.variable);
        text3.setText(item.getmVariable());
    }
    @Override
    protected boolean matches(CustomListViewClass value, CharSequence prefix, CharSequence lowerCasePrefix) {
        return value.getmName().toLowerCase().contains(lowerCasePrefix);
    }
}