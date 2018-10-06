package com.kackerlacka.mechie;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private int menuItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigation drawer setup
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        nvDrawer = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(nvDrawer);
        nvDrawer.getMenu().getItem(0).setChecked(true);
        mDrawer.addDrawerListener(drawerToggle);

        // Toolbar setup
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);


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

    //Launch Mechanical Properties Calculator on button click
    public void hardnessConverter(View view) {
        Intent intent = new Intent(this, ToolsHardnessConverter.class);
        startActivity(intent);
    }

    //Launch Se Calculator on button click
    public void dimensionlessNumbers(View view) {
        Intent intent = new Intent(this, ToolsDimensionlessNumbersCalc.class);
        startActivity(intent);
    }

    //Launch Bolt Sizer on button click
    public void boltSizer(View view) {
        Intent intent = new Intent(this, ToolsBoltSizer.class);
        startActivity(intent);
    }

    //Launch SAE Bolt Picker on button click
    public void saePicker(View view) {
        Intent intent = new Intent(this, ToolsSAEBoltPicker.class);
        startActivity(intent);
    }

    //Launch SAE Bolt Picker on button click
    public void metalProperties(View view) {
        Intent intent = new Intent(this, MaterialsPropertiesMetal.class);
        startActivity(intent);
    }

    //Launch SAE Bolt Picker on button click
    public void fluidProperties(View view) {
        Intent intent = new Intent(this, MaterialsPropertiesFluids.class);
        startActivity(intent);
    }

    //Launch Mechanics Concepts on button click
    public void mechanicsActivity(View view) {
        Intent intent = new Intent(this, ConceptsMechanics.class);
        startActivity(intent);
    }

    //Launch Materials Concepts on button click
    public void materialsActivity(View view) {
        Intent intent = new Intent(this, ConceptsMaterials.class);
        startActivity(intent);
    }

    //Launch Materials Concepts on button click
    public void thermalengineeringActivity(View view) {
        Intent intent = new Intent(this, ConceptsThermalEngineering.class);
        startActivity(intent);
    }

    //Launch Materials Concepts on button click
    public void mathematicsActivity(View view) {
        Intent intent = new Intent(this, ConceptsMathematics.class);
        startActivity(intent);
    }

    //Launch Chemistry Concepts on button click
    public void chemistryActivity(View view) {
        Intent intent = new Intent(this, ConceptsChemistry.class);
        startActivity(intent);
    }

    //Launch Physics Concepts on button click
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
            menuItemId = menuItem.getItemId();
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
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
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
}
