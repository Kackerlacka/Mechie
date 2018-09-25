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
        equationsList.add(new CustomListViewClass( "Atomic Mass Constant [mᵤ]", "1.660 x 10⁻²⁷ kg", 17));
        equationsList.add(new CustomListViewClass( "Avogadro's Number [Nₐ]" , "6.023 x 10²³ 1/mol", 2));
        equationsList.add(new CustomListViewClass( "Boltzmann Constant [k]", "1.3806 x 10⁻²³ J/K", 14));
        equationsList.add(new CustomListViewClass( "Electron Mass [mₑ]", "9.109 x 10⁻³¹ kg", 18));
        equationsList.add(new CustomListViewClass( "Electric Constant [ε₀]", "8.854 x 10⁻¹² C²/Nm²", 10));
        equationsList.add(new CustomListViewClass( "Elementary Charge [e]", "1.602 x 10⁻¹⁹ C", 9));
        equationsList.add(new CustomListViewClass( "Euler's Constant [g]" , "0.57721566", 3));
        equationsList.add(new CustomListViewClass( "Gas Constant [R]", "8.3145 J/molK", 15));
        equationsList.add(new CustomListViewClass( "Golden Ratio [φ]" , "1.61803398", 4));
        equationsList.add(new CustomListViewClass( "Gravitational Constant [G]" , "6.67 x 10⁻¹¹ Nm²/kg²", 4));
        equationsList.add(new CustomListViewClass( "Magnetic Constant [μ₀]", "4π x 10⁻⁷ Tm/A", 11));
        equationsList.add(new CustomListViewClass( "Natural Log Base [e]" , "2.71828182", 5));
        equationsList.add(new CustomListViewClass( "Neutron Mass [mₙ]", "1.674 x 10⁻²⁷ kg", 20));
        equationsList.add(new CustomListViewClass( "Permeability of Free Space [μ₀]", "4π x 10⁻⁷ Tm/A", 12));
        equationsList.add(new CustomListViewClass( "Permitivitty of Free Space [ε₀]", "8.854 x 10⁻¹² C²/Nm²", 10));
        equationsList.add(new CustomListViewClass( "Pi [π]" , "3.14159265", 6));
        equationsList.add(new CustomListViewClass( "Planck Constant [h]", "6.626 x 10⁻³⁴ Js (4.136 x 10⁻¹⁵ eVs)", 8));
        equationsList.add(new CustomListViewClass( "Proton Mass [mₚ]", "1.672 x 10⁻²⁷ kg", 19));
        equationsList.add(new CustomListViewClass( "Speed of Light in a Vacuum [c]" , "299,792,458 (m/s)", 7));
        equationsList.add(new CustomListViewClass( "Stefan-Boltzmann Constant [σ]", "5.670 x 10⁻⁸ W/m²K⁴", 14));
        equationsList.add(new CustomListViewClass( "Vacuum Permeability [μ₀]", "4π x 10⁻⁷ Tm/A", 13));
        equationsList.add(new CustomListViewClass( "Vacuum Permitivitty [ε₀]", "8.854 x 10⁻¹² C²/Nm²", 10));
        equationsList.add(new CustomListViewClass( "Wien Displacement Constant [b]", "2.898 mm/K (58.78 GHz/K)", 16));


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
