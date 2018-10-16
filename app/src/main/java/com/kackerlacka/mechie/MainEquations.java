package com.kackerlacka.mechie;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import java.util.Objects;

public class MainEquations extends ListFragment {

    private ListView listView; //Main listview
    private CustomListviewAdapter mAdapter; //Custom adapter for listview
    public static final String TAG = MainEquations.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_equations, container, false);
        setHasOptionsMenu(true);

        //Find listview in xml
        listView = view.findViewById(R.id.listView);
        ArrayList<CustomListViewClass> equationsList = new ArrayList<>();

        //Populate listview with items
        equationsList.add(new CustomListViewClass( "Acceleration" , "a", "Δv/Δt", 1));
        equationsList.add(new CustomListViewClass( "Angular Acceleration" , "α", "aₜ/r", 61));
        equationsList.add(new CustomListViewClass( "Angular Displacement", "θ", "S/r", 58));
        equationsList.add(new CustomListViewClass( "Angular Velocity" , "ω", "v/r", 3));
        equationsList.add(new CustomListViewClass( "Average Angular Acceleration" , "α", "Δω/Δt", 59));
        equationsList.add(new CustomListViewClass( "Average Angular Velocity" , "ω", "Δθ/Δt", 60));
        equationsList.add(new CustomListViewClass( "Belt Velocity" , "v", "(πdₘnₘ)/12", 4));
        equationsList.add(new CustomListViewClass( "Bolt Stress Area" , "A", "π/4(dₙ - 0.9743/n)²", 5));
        equationsList.add(new CustomListViewClass( "Brake Clamp Load" , "C", "Placeholder", 6));
        equationsList.add(new CustomListViewClass( "Buoyant Force" , "Fᵦ", "Placeholder", 7));
        equationsList.add(new CustomListViewClass( "Conductivity" , "σ", "Placeholder", 8));
        equationsList.add(new CustomListViewClass( "Density" , "ρ", "Placeholder", 10));
        equationsList.add(new CustomListViewClass( "Drag Force" , "F\uD835\uDCB9", "Placeholder", 11));
        equationsList.add(new CustomListViewClass( "Dynamic Viscosity" , "μ", "Placeholder", 12));
        equationsList.add(new CustomListViewClass( "Elastic Potential Energy" , "PEₑ", "Placeholder", 13));
        equationsList.add(new CustomListViewClass( "Electric Field" , "E", "Placeholder", 14));
        equationsList.add(new CustomListViewClass( "Engineering Strain" , "ε", "ΔL/L₀", 15));
        equationsList.add(new CustomListViewClass( "Engineering Stress" , "σ", "F/A₀", 16));
        equationsList.add(new CustomListViewClass( "Escape Velocity" , "v", "Placeholder", 17));
        equationsList.add(new CustomListViewClass( "Fluid Pressure" , "P", "Placeholder", 19));
        equationsList.add(new CustomListViewClass( "Fluid Surface Tension" , "γ", "Placeholder", 20));
        equationsList.add(new CustomListViewClass( "Force" , "F", "m⋅a", 21));
        equationsList.add(new CustomListViewClass( "Fracture Toughness" , "Kᵢ\uD835\uDCB8", "Yσ⋅Sqrt(πa)", 22));
        equationsList.add(new CustomListViewClass( "Gauss Law" , "ϕ", "Placeholder", 23));
        equationsList.add(new CustomListViewClass( "Gibb's Free Energy" , "G", "Placeholder", 24));
        equationsList.add(new CustomListViewClass( "Gravitational Force" , "g", "Placeholder", 25));
        equationsList.add(new CustomListViewClass( "Gravitational Potential Energy" , "PE\uD835\uDCF0", "mgh", 26));
        equationsList.add(new CustomListViewClass( "Horsepower using Belt Velocity/Force" , "HP", "(Fb⋅Vb)/33000", 27));
        equationsList.add(new CustomListViewClass( "Ideal Gas Law" , "PV", "PV = nRT", 28));
        equationsList.add(new CustomListViewClass( "Induced Voltage" , "ε", "Placeholder", 29));
        equationsList.add(new CustomListViewClass( "Kinematic Viscosity" , "ν", "Placeholder", 30));
        equationsList.add(new CustomListViewClass( "Kinetic Energy" , "KE", "(1/2)mv²", 31));
        equationsList.add(new CustomListViewClass( "Mach Number" , "Ma", "u/v", 32));
        equationsList.add(new CustomListViewClass( "Magnetic Force Charge" , "F", "Placeholder", 33));
        equationsList.add(new CustomListViewClass( "Moment" , "M", "F⋅d", 34));
        equationsList.add(new CustomListViewClass( "Momentum" , "ρ", "Placeholder", 35));
        equationsList.add(new CustomListViewClass( "Nernst Equation" , "ΔG", "Placeholder", 36));
        equationsList.add(new CustomListViewClass( "Ohms Law" , "V", "I⋅R", 37));
        equationsList.add(new CustomListViewClass( "Open Channel Water Flow" , "V", "Placeholder", 38));
        equationsList.add(new CustomListViewClass( "Orbital Velocity" , "v", "Placeholder", 39));
        equationsList.add(new CustomListViewClass( "Orifice Discharge" , "Q", "Placeholder", 40));
        equationsList.add(new CustomListViewClass( "Pump Power" , "P", "Placeholder", 41));
        equationsList.add(new CustomListViewClass( "Refraction" , "n", "Placeholder", 42));
        equationsList.add(new CustomListViewClass( "Resistivity" , "ρ", "Placeholder", 43));
        equationsList.add(new CustomListViewClass( "Resonant Frequency" , "f", "Placeholder", 44));
        equationsList.add(new CustomListViewClass( "Reynolds Number" , "Re", "Placeholder", 45));
        equationsList.add(new CustomListViewClass( "Rotational Kinetic Energy" , "KE", "Placeholder", 46));
        equationsList.add(new CustomListViewClass( "Shear Modulus" , "G", "τ/γ", 47));
        equationsList.add(new CustomListViewClass( "Shear Strain" , "γ", "τ/G", 48));
        equationsList.add(new CustomListViewClass( "Shear Stress" , "τ", "F/A", 49));
        equationsList.add(new CustomListViewClass( "Shear Stress, Linear Helical Spring" , "τ", "Kₛ(8FD/πd³)", 50));
        equationsList.add(new CustomListViewClass( "Surface Charge Density" , "σ", "Placeholder", 51));
        equationsList.add(new CustomListViewClass( "Torque" , "T", "Placeholder", 53));
        equationsList.add(new CustomListViewClass( "Velocity" , "v", "Placeholder", 54));
        equationsList.add(new CustomListViewClass( "Voltage Divider" , "V", "Placeholder", 55));
        equationsList.add(new CustomListViewClass( "Voltage Drop" , "V", "Placeholder", 56));
        equationsList.add(new CustomListViewClass( "Young's Modulus" , "E", "σ/ε", 57));

        mAdapter = new CustomListviewAdapter(getActivity(),equationsList);
        TextView mEmptyView = view.findViewById(R.id.emptyView);
        listView.setAdapter(mAdapter);
        listView.setEmptyView(mEmptyView);

        //Set onClick functions for listview
        listView.setOnItemClickListener((AdapterView<?> adapter, View arg1,
        int position, long arg3) -> {

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
        FragmentManager fm = Objects.requireNonNull(getActivity()).getFragmentManager();
        CustomEquationsDialog dialog = new CustomEquationsDialog();
        dialog.setArguments(info);
        dialog.show(fm, TAG);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
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
