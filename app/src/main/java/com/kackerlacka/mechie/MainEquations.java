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
import android.widget.TextView;

import java.util.ArrayList;

public class MainEquations extends ListFragment {

    private ListView listView; //Main listview
    private CustomListviewAdapter mAdapter; //Custom adapter for listview
    private AlertDialog.Builder builder;
    public static final String TAG = MainEquations.class.getSimpleName();
    private TextView mEmptyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_equations, container, false);
        setHasOptionsMenu(true);

        //Find listview in xml
        listView = (ListView) view.findViewById(R.id.listView);
        ArrayList<CustomListViewClass> equationsList = new ArrayList<>();

        //Populate listview with items
        equationsList.add(new CustomListViewClass( "Acceleration" , "Δv/Δt", 1));
        equationsList.add(new CustomListViewClass( "Angular Acceleration" , "aₜ/r", 61));
        equationsList.add(new CustomListViewClass( "Angular Displacement", "S/r", 58));
        equationsList.add(new CustomListViewClass( "Angular Velocity" , "v/r", 3));
        equationsList.add(new CustomListViewClass( "Average Angular Acceleration" , "Δω/Δt", 59));
        equationsList.add(new CustomListViewClass( "Average Angular Velocity" , "Δθ/Δt", 60));
        equationsList.add(new CustomListViewClass( "Belt Velocity" , "(πdₘnₘ)/12", 4));
        equationsList.add(new CustomListViewClass( "Bolt Stress Area" , "π/4(dₙ - 0.9743/n)²", 5));
        equationsList.add(new CustomListViewClass( "Brake Clamp Load" , "Placeholder", 6));
        equationsList.add(new CustomListViewClass( "Buoyant Force" , "Placeholder", 7));
        equationsList.add(new CustomListViewClass( "Conductivity" , "Placeholder", 8));
        equationsList.add(new CustomListViewClass( "Coulomb's Law" , "Placeholder", 9));
        equationsList.add(new CustomListViewClass( "Darcy's Law" , "Placeholder", 9));
        equationsList.add(new CustomListViewClass( "Density" , "Placeholder", 10));
        equationsList.add(new CustomListViewClass( "Drag Force" , "Placeholder", 11));
        equationsList.add(new CustomListViewClass( "Dynamic Viscosity" , "Placeholder", 12));
        equationsList.add(new CustomListViewClass( "Elastic Potential Energy" , "Placeholder", 13));
        equationsList.add(new CustomListViewClass( "Electric Field" , "Placeholder", 14));
        equationsList.add(new CustomListViewClass( "Engineering Strain" , "ΔL/L₀", 15));
        equationsList.add(new CustomListViewClass( "Engineering Stress" , "F/A₀", 16));
        equationsList.add(new CustomListViewClass( "Escape Velocity" , "Placeholder", 17));
        equationsList.add(new CustomListViewClass( "Flow Head Loss" , "Placeholder", 18));
        equationsList.add(new CustomListViewClass( "Fluid Pressure" , "Placeholder", 19));
        equationsList.add(new CustomListViewClass( "Fluid Surface Tension" , "Placeholder", 20));
        equationsList.add(new CustomListViewClass( "Force" , "m⋅a", 21));
        equationsList.add(new CustomListViewClass( "Fracture Toughness" , "Yσ⋅Sqrt(πa)", 22));
        equationsList.add(new CustomListViewClass( "Gauss Law" , "Placeholder", 23));
        equationsList.add(new CustomListViewClass( "Gibb's Free Energy" , "Placeholder", 24));
        equationsList.add(new CustomListViewClass( "Gravitational Force" , "Placeholder", 25));
        equationsList.add(new CustomListViewClass( "Gravitational Potential Energy" , "Placeholder", 26));
        equationsList.add(new CustomListViewClass( "Horsepower using Belt Velocity/Force" , "(Fb⋅Vb)/33000", 27));
        equationsList.add(new CustomListViewClass( "Ideal Gas Law" , "Placeholder", 28));
        equationsList.add(new CustomListViewClass( "Induced Voltage" , "Placeholder", 29));
        equationsList.add(new CustomListViewClass( "Kinematic Viscosity" , "Placeholder", 30));
        equationsList.add(new CustomListViewClass( "Kinetic Energy" , "Placeholder", 31));
        equationsList.add(new CustomListViewClass( "Mach Number" , "Placeholder", 32));
        equationsList.add(new CustomListViewClass( "Magnetic Force Charge" , "Placeholder", 33));
        equationsList.add(new CustomListViewClass( "Moment" , "F⋅d", 34));
        equationsList.add(new CustomListViewClass( "Momentum" , "Placeholder", 35));
        equationsList.add(new CustomListViewClass( "Nernst Equation" , "Placeholder", 36));
        equationsList.add(new CustomListViewClass( "Ohms Law" , "Placeholder", 37));
        equationsList.add(new CustomListViewClass( "Open Channel Water Flow" , "Placeholder", 38));
        equationsList.add(new CustomListViewClass( "Orbital Velocity" , "Placeholder", 39));
        equationsList.add(new CustomListViewClass( "Orifice Discharge" , "Placeholder", 40));
        equationsList.add(new CustomListViewClass( "Pump Power" , "Placeholder", 41));
        equationsList.add(new CustomListViewClass( "Refraction" , "Placeholder", 42));
        equationsList.add(new CustomListViewClass( "Resistivity" , "Placeholder", 43));
        equationsList.add(new CustomListViewClass( "Resonant Frequency" , "Placeholder", 44));
        equationsList.add(new CustomListViewClass( "Reynolds Number" , "Placeholder", 45));
        equationsList.add(new CustomListViewClass( "Rotational Kinetic Energy" , "Placeholder", 46));
        equationsList.add(new CustomListViewClass( "Shear Modulus" , "τ/γ", 47));
        equationsList.add(new CustomListViewClass( "Shear Strain" , "τ/G", 48));
        equationsList.add(new CustomListViewClass( "Shear Stress" , "F/A", 49));
        equationsList.add(new CustomListViewClass( "Shear Stress of Linear Helical Spring" , "Kₛ(8FD/πd³)", 50));
        equationsList.add(new CustomListViewClass( "Surface Charge Density" , "Placeholder", 51));
        equationsList.add(new CustomListViewClass( "Thermal Deformation" , "αL(T - T₀)", 52));
        equationsList.add(new CustomListViewClass( "Torque" , "Placeholder", 53));
        equationsList.add(new CustomListViewClass( "Velocity" , "Placeholder", 54));
        equationsList.add(new CustomListViewClass( "Voltage Divider" , "Placeholder", 55));
        equationsList.add(new CustomListViewClass( "Voltage Drop" , "Placeholder", 56));
        equationsList.add(new CustomListViewClass( "Young's Modulus" , "σ/ε", 57));

        mAdapter = new CustomListviewAdapter(getActivity(),equationsList);
        mEmptyView = (TextView)view.findViewById(R.id.emptyView);
        listView.setAdapter(mAdapter);
        listView.setEmptyView(mEmptyView);

        //Set onClick functions for listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View arg1,
                                    int position, long arg3) {

                CustomListViewClass item = mAdapter.getItem(position);

                switch(item.mID) {
                    case 61:
                        setVariables("Angular Acceleration",
                                "<center>$$\\alpha = \\frac{a_t}{r}$$</center>",
                                "\\(\\frac{rad}{s^2}\\)",
                                "\\(a_t = \\mathrm{tangential~acceleration~in~}\\frac{m}{s^2}, \\frac{ft}{s^2}\\)",
                                "\\(r = \\mathrm{radius~of~circular~path~in~}m, ft\\)");
                        break;
                    case 58:
                        setVariables("Angular Displacement",
                                "<center>$$\\theta = \\frac{S}{r}$$</center>",
                                "\\(radians, degrees\\)",
                                "\\(S = \\mathrm{arc~length~in~}m, ft\\)",
                                "\\(r = \\mathrm{radius~of~circular~path~in~}m, ft\\)");
                        break;
                    case 3:
                        setVariables("Angular Velocity",
                                "<center>$$\\omega = \\frac{v}{r}$$</center>",
                                "\\(radians, degrees\\)",
                                "\\(v = \\mathrm{velocity~in~}\\frac{m}{s}, \\frac{ft}{s}\\)",
                                "\\(r = \\mathrm{radius~of~circular~path~in~}m, ft\\)");
                        break;
                    case 1:
                        setVariables("Acceleration",
                                "<center>$$a = \\frac{\\Delta v}{\\Delta t}$$</center>",
                                "\\(\\frac{m}{s^2}\\, \\frac{ft}{s^2}\\)",
                                "\\(\\Delta v = \\mathrm{change~in~velocity~in~}\\frac{m}{s}, \\frac{ft}{s}\\)",
                                "\\(\\Delta t = \\mathrm{change~in~time~in~}s\\)");
                        break;
                    case 2:
                        setVariables("Angular Acceleration",
                                "<center>$$\\alpha = \\frac{\\Delta \\omega}{\\Delta t}$$</center>",
                                "\\(\\frac{m}{s^2}\\, \\frac{ft}{s^2}\\)",
                                "\\(\\Delta \\omega = \\mathrm{change~in~angular~velocity~in~}\\frac{rad}{s}\\)",
                                "\\(\\Delta t = \\mathrm{change~in~time~in~}s\\)");
                        break;
                    case 15:
                    setVariables("Engineering Strain",
                            "<center>$$\\epsilon = \\frac{\\Delta L}{L_0}$$</center>",
                            "\\(Unitless\\)",
                            "\\(\\Delta L = \\mathrm{change~in~length~in~}in, mm\\)",
                            "\\(L_0 = \\mathrm{original~area~in~}in, mm\\)");
                    break;
                    case 16:
                    setVariables("Engineering Stress",
                            "<center>$$\\sigma = \\frac{F}{A_0}$$</center>",
                            "\\(Pa, MPa, psi, kpsi\\)",
                            "\\(F = \\mathrm{axial~force~in~}N, kip\\)",
                            "\\(A_0 = \\mathrm{cross~sectional~area~in~} m^2, ft^2\\)");
                    break;
                    case 34:
                    setVariables("Moment",
                            "<center>$$M= F{\\cdot}d$$</center>",
                            "\\(lb{\\cdot}ft, N{\\cdot}m\\)",
                            "\\(F = \\mathrm{force~in~}N, lb\\)",
                            "\\(d = \\mathrm{distance~of~radial~arm~in~} m, ft\\)");
                    break;
                    case 49:
                    setVariables("Shear Stress",
                            "<center>$$\\epsilon = \\frac{\\Delta L}{L_0}$$</center>",
                            "\\(Unitless\\)",
                            "\\(\\Delta L = \\mathrm{change~in~length~in~}in, mm\\)",
                            "\\(L_0 = \\mathrm{original~area~in~}in, mm\\)");
                    break;

                }
            }
        });

        return view;

    }

    public void setVariables(String title, String equation, String units, String variable_one, String variable_two) {
        Bundle info = new Bundle();
        info.putString("KEY_TITLE", title);
        info.putString("KEY_EQUATION", equation);
        info.putString("KEY_UNITS", units);
        info.putString("KEY_VARIABLES_ONE", variable_one);
        info.putString("KEY_VARIABLES_TWO", variable_two);
        FragmentManager fm = getActivity().getFragmentManager();
        CustomEquationsDialog dialog = new CustomEquationsDialog();
        dialog.setArguments(info);
        dialog.show(fm, TAG);
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
                    listView.clearTextFilter();
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
