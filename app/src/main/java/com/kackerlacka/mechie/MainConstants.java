package com.kackerlacka.mechie;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ListView;
import android.util.Log;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;

public class MainConstants extends ListFragment {

    private ListView listView; //Main listview
    private CustomListviewAdapter mAdapter; //Custom adapter for listview
    private AlertDialog.Builder builder;
    public static final String TAG = MainEquations.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_constants, container, false);
        setHasOptionsMenu(true);

        //Find listview in xml
        listView = (ListView) view.findViewById(R.id.listView);
        ArrayList<CustomListViewClass> equationsList = new ArrayList<>();

        //Populate listview with items
        equationsList.add(new CustomListViewClass( "Acceleration of Gravity (earth) [g]" , "32.176 ft/s² (9.81 m/s²)", 1));
        equationsList.add(new CustomListViewClass( "Avogadro's Number [N]" , "6.023 x 10²³", 2));
        equationsList.add(new CustomListViewClass( "Euler's Constant [g]" , "0.57721566", 2));
        equationsList.add(new CustomListViewClass( "Golden Ratio [φ]" , "1.61803398", 2));
        equationsList.add(new CustomListViewClass( "Natural Log Base [e]" , "2.71828182", 3));
        equationsList.add(new CustomListViewClass( "Pi [π]" , "3.14159265", 4));
        equationsList.add(new CustomListViewClass("Stefan-Boltzman constant [σ]", "5.67 x 10⁻⁸", 5));


        mAdapter = new CustomListviewAdapter(getActivity(),equationsList);
        listView.setAdapter(mAdapter);

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Stuff
    }

    //Options menu created
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_equations, menu);
        super.onCreateOptionsMenu(menu,inflater);

        final SearchView searchView2 = (SearchView) menu.findItem(R.id.item_search).getActionView();

        searchView2.setIconifiedByDefault(false);
        searchView2.setQueryHint("Search Here");
        searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                Filter filter = mAdapter.getFilter();
                if (TextUtils.isEmpty(newText)) {
                    listView.clearTextFilter(); //Filter items as text changes
                    filter.filter(newText);
                } else {
                    filter.filter(newText);
                    listView.setFilterText(newText);
                }
                mAdapter.notifyDataSetChanged();
                return true;
            }
            public boolean onQueryTextSubmit(String query) {
                searchView2.clearFocus(); //Close searchview when enter button pressed
                return true;
            }
        });

    }

}
