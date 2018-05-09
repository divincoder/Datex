package com.datex.datex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.datex.datex.R;
import com.datex.datex.fragment.AllPatientFragment;
import com.datex.datex.fragment.HomeFragment;

public class NavigationActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private Bundle fragBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.label_home));
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //switchFragment(this, HomeFragment.newInstance(null));
        getSupportFragmentManager().beginTransaction().replace(R.id.container, HomeFragment.newInstance(null))
                .commit();
    }

    public Toolbar getToolbar() {
        if (toolbar == null) {
            toolbar = findViewById(R.id.toolbar);
        }
        return toolbar;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (id) {
            case R.id.action_search:
                break;
            case R.id.action_notification:
                Intent intent = new Intent(NavigationActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        fragBundle = null;
        Intent intent;
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_home:
                switchFragment(this, HomeFragment.newInstance(fragBundle));
                break;
            case R.id.nav_all_patients:
                switchFragment(this, AllPatientFragment.newInstance(null));
                break;
            case R.id.nav_notification:
                intent = new Intent(NavigationActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_add_patient:
                intent = new Intent(NavigationActivity.this, AddPatientActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

//    public void onFragmentChanged(Fragment fragment) {
//        this.frag = fragment;
//    }
}
