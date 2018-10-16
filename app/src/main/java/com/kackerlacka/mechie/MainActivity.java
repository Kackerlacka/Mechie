package com.kackerlacka.mechie;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigation drawer setup
        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        NavigationView nvDrawer = findViewById(R.id.nav_view);
        setupDrawerContent(nvDrawer);
        nvDrawer.getMenu().getItem(0).setChecked(true);
        mDrawer.addDrawerListener(drawerToggle);

        // Toolbar setup
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getSupportActionBar() != null) getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);


        Fragment fragment2 = null;
        Class fragmentClass2;
        fragmentClass2 = MainConcepts.class;
        try {
            fragment2 = (Fragment) fragmentClass2.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment2).commit();

        setTitle("Concepts");

    }

    //Hardness Converter
    public void hardnessConverter(View view) {
        Intent intent = new Intent(this, ToolsHardnessConverter.class);
        startActivity(intent);
    }

    //Dimensionless Numbers Calculator
    public void dimensionlessNumbers(View view) {
        Intent intent = new Intent(this, ToolsDimensionlessNumbersCalc.class);
        startActivity(intent);
    }

    //SAE Picker (Bolt Sizer)
    public void saePicker(View view) {
        Intent intent = new Intent(this, ToolsSAEBoltPicker.class);
        startActivity(intent);
    }

    //Buckingham Pi Theorem
    public void piTheorem(View view) {
        Intent intent = new Intent(this, ToolsPiCalculator.class);
        startActivity(intent);
    }

    //Physics Concepts
    public void springDesigner(View view) {
        Intent intent = new Intent(this, ToolsHelicalSpringDesigner.class);
        startActivity(intent);
    }

    //Metal Properties
    public void metalProperties(View view) {
        Intent intent = new Intent(this, MaterialsPropertiesMetal.class);
        startActivity(intent);
    }

    //Fluid Properties
    public void fluidProperties(View view) {
        Intent intent = new Intent(this, MaterialsPropertiesFluids.class);
        startActivity(intent);
    }

    //Mechanics Concepts
    public void mechanicsActivity(View view) {
        Intent intent = new Intent(this, ConceptsMechanics.class);
        startActivity(intent);
    }

    //Materials Concepts
    public void materialsActivity(View view) {
        Intent intent = new Intent(this, ConceptsMaterials.class);
        startActivity(intent);
    }

    //Thermal Engineering Concepts
    public void thermalEngineeringActivity(View view) {
        Intent intent = new Intent(this, ConceptsThermalEngineering.class);
        startActivity(intent);
    }

    //Mathematics Concepts
    public void mathematicsActivity(View view) {
        Intent intent = new Intent(this, ConceptsMathematics.class);
        startActivity(intent);
    }

    //Chemistry Concepts
    public void chemistryActivity(View view) {
        Intent intent = new Intent(this, ConceptsChemistry.class);
        startActivity(intent);
    }

    //Physics Concepts
    public void physicsActivity(View view) {
        Intent intent = new Intent(this, ConceptsPhysics.class);
        startActivity(intent);
    }

    public void selectDrawerItem(final MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item selected
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_concepts:
                fragmentClass = MainConcepts.class;
                break;
            case R.id.nav_equations:
                fragmentClass = MainEquations.class;
                break;
            case R.id.nav_constants:
                fragmentClass = MainConstants.class;
                break;
            case R.id.nav_materials:
                fragmentClass = MainMaterials.class;
                break;
            case R.id.nav_tools:
                fragmentClass = MainTools.class;
                break;
            case R.id.nav_about:
                fragmentClass = MainAbout.class;
                break;
            case R.id.nav_copyleft:
                fragmentClass = MainCopyleft.class;
                break;
            case R.id.nav_sources:
                fragmentClass = MainSources.class;
                break;
            default:
                fragmentClass = MainConcepts.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            menuItem.getItemId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // Highlight the selected item
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
        // Set value of menuItemId to be used for Spinner above
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener((@NonNull MenuItem menuItem) -> {
                        selectDrawerItem(menuItem);
                        return true;
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        DrawerLayout layout = findViewById(R.id.drawer_layout);
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START);
        }
        else if(doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
