package com.shamba.amoi.shambaapp.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.fragments.HomeFragment;
import com.shamba.amoi.shambaapp.fragments.assets.AssetFragment;
import com.shamba.amoi.shambaapp.fragments.product.ProductStockFragment;
import com.shamba.amoi.shambaapp.fragments.product.ProductsFragment;
import com.shamba.amoi.shambaapp.fragments.product.RestockProductFragment;
import com.shamba.amoi.shambaapp.fragments.labor.ResourcesFragment;
import com.shamba.amoi.shambaapp.fragments.power.PowerSourcesFragment;
import com.shamba.amoi.shambaapp.fragments.projects.CreatePlantingProgrammeFragment;
import com.shamba.amoi.shambaapp.fragments.projects.PlantingPhasesFragment;
import com.shamba.amoi.shambaapp.fragments.projects.PlantingProgrammesFragment;
import com.shamba.amoi.shambaapp.fragments.projects.TaskListFragment;
import com.shamba.amoi.shambaapp.fragments.projects.TaskSchedulingFragment;
import com.shamba.amoi.shambaapp.fragments.reports.ReportsFragment;

import java.util.LinkedHashMap;
import java.util.Map;

//public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
public class HomeActivity extends BaseActivity  {
    protected DrawerLayout navigationDrawer;
    private NavigationView navigationView;
    private Map<String, Fragment> fragmentMap;
    private int currentSelectedPosition;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadHomeFragment();
        setupFragments();
        setupToolbar();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setNavDrawer();
    }
    /**
     * Load home fragment
     */
    private void loadHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                new HomeFragment()).commit();
    }
    /**
     * Setup navigation drawer for the App.
     */
    public void setNavDrawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        Fragment  f = getSupportFragmentManager().findFragmentById(R.id.fragment_placeholder_home);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        //Handle projects back presses
        else if (f instanceof PlantingProgrammesFragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                    new HomeFragment()).addToBackStack(null).commit();
        }
        else if (f instanceof CreatePlantingProgrammeFragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                    new PlantingProgrammesFragment()).addToBackStack(null).commit();
        }
        else if (f instanceof PlantingPhasesFragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                    new PlantingProgrammesFragment()).addToBackStack(null).commit();
        }
        else if (f instanceof TaskListFragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                    new PlantingPhasesFragment()).addToBackStack(null).commit();
        }
        else if (f instanceof TaskSchedulingFragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                    new PlantingPhasesFragment()).addToBackStack(null).commit();
        }

        //manages back presses for products
        else if (f instanceof ProductsFragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                    new HomeFragment()).addToBackStack(null).commit();
        }
        else if (f instanceof ProductStockFragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                    new ProductsFragment()).addToBackStack(null).commit();
        }
        else if (f instanceof RestockProductFragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                    new ProductsFragment()).addToBackStack(null).commit();
        }
//        else if (f instanceof InventoryUtilizationFragment) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
//                    new ProductsFragment()).addToBackStack(null).commit();
//        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home_drawer, menu);
//        getMenuInflater().inflate(R.menu.search_menu, menu);

//        SearchManager searchManager = (SearchManager)
//                getSystemService(Context.SEARCH_SERVICE);
//       MenuItem searchMenuItem = menu.findItem(R.id.search);
//        android.widget.SearchView searchView = (android.widget.SearchView)
//                searchMenuItem.getActionView();
////        searchMenuItem.setVisible(false);
//
//        searchView.setSearchableInfo(searchManager.
//                getSearchableInfo(getComponentName()));
//        searchView.setSubmitButtonEnabled(true);
//
//        searchView.setOnQueryTextListener((android.widget.SearchView.OnQueryTextListener) this);

        return true;
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_menu, menu);
//
//        SearchManager searchManager = (SearchManager)
//                getSystemService(Context.SEARCH_SERVICE);
//        searchMenuItem = menu.findItem(R.id.search);
//        searchView = (SearchView) searchMe      nuItem.getActionView();
//
//        searchView.setSearchableInfo(searchManager.
//                getSearchableInfo(getComponentName()));
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setOnQueryTextListener(this);
//
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

//        if (id == R.id.search) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (menuItem.isChecked())
            menuItem.setChecked(false);
        else
            menuItem.setChecked(true);

        switch (menuItem.getItemId()) {

            case R.id.nav_home:
                currentSelectedPosition = 0;
                selectFragment(currentSelectedPosition);
                return true;
            case R.id.nav_products:
                currentSelectedPosition = 1;
                selectFragment(currentSelectedPosition);
                return true;
            case R.id.nav_projects:
                currentSelectedPosition = 2;
                selectFragment(currentSelectedPosition);
                return true;
            case R.id.nav_labor:
                currentSelectedPosition = 3;
                selectFragment(currentSelectedPosition);
                return true;
            case R.id.nav_power:
                currentSelectedPosition = 4;
                selectFragment(currentSelectedPosition);
                return true;
            case R.id.nav_assets:
                currentSelectedPosition = 5;
                selectFragment(currentSelectedPosition);
                return true;
            case R.id.nav_reports:
                currentSelectedPosition = 6;
                selectFragment(currentSelectedPosition);
                return true;

            case R.id.nav_logout:
                currentSelectedPosition = 7;
                return true;
//            default:
//                return true;
        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Provides the action bar instance.
     * @return the action bar.
     */
    public ActionBar getActionBarToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }
        return getSupportActionBar();
    }
//    private void setupHeader() {
//        if (isLoggedIn) {
//            User user = sessionManager.getUserDetails();
//            if (user != null) {
//                textViewUsername.setVisibility(View.VISIBLE);
//                buttonLogin.setVisibility(View.GONE);
//                textViewUsername.setText(user.getFullName());
//            }
//        } else {
//            textViewUsername.setVisibility(View.GONE);
//            buttonLogin.setVisibility(View.VISIBLE);
//        }
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
//    }

    private void setupFragments() {
        fragmentMap = new LinkedHashMap<>();
        fragmentMap.put(getString(R.string.title_fragment_home), new HomeFragment());
        fragmentMap.put(getString(R.string.title_fragment_Products), new ProductsFragment());
        fragmentMap.put(getString(R.string.title_fragment_projects), new PlantingProgrammesFragment());
        fragmentMap.put(getString(R.string.title_fragment_labor), new ResourcesFragment());
        fragmentMap.put(getString(R.string.title_fragment_power), new PowerSourcesFragment());
        fragmentMap.put(getString(R.string.title_fragment_assets), new AssetFragment());
        fragmentMap.put(getString(R.string.title_fragment_reports),
                new ReportsFragment());

//        fragmentMap.put(getString(R.string.title_fragment_reports), new AssetFragment());

    }

    private void selectFragment(int position) {
        Fragment fragment = (Fragment) fragmentMap.values().toArray()[position];
        String fragmentTitle = (String) fragmentMap.keySet().toArray()[position];
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = null;
        if (position != 0) {
            fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);
        } else {
            fragmentTransaction = fragmentManager.beginTransaction();
        }

        fragmentTransaction.replace(R.id.fragment_placeholder_home, fragment).commit();
        // update selected item and title, then close the drawer
        navigationView.getMenu().getItem(position).setChecked(true);

        Log.d("Title", fragmentTitle);
        setTitle(fragmentTitle);
        drawer.closeDrawers();
    }


    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationDrawer.openDrawer(GravityCompat.START);
            }
        });
    }

    public void onClickInventory(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                new ProductsFragment()).addToBackStack(null).commit();
    }
    public void onClickProjects(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                new PlantingProgrammesFragment()).addToBackStack(null).commit();
    }

    public void onClickLabor(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                new ResourcesFragment()).addToBackStack(null).commit();
    }

    public void onClickPowerButton(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                new PowerSourcesFragment()).addToBackStack(null).commit();
    }

    public void onClickAssets(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                new AssetFragment()).addToBackStack(null).commit();
    }

    public void onClickReports(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_placeholder_home,
                new ReportsFragment()).addToBackStack(null).commit();
    }

//    @Override
//    public boolean onQueryTextSubmit(String s) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String s) {
//        return false;
//    }
}
