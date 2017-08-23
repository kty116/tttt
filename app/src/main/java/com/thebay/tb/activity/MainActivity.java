package com.thebay.tb.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.thebay.tb.R;
import com.thebay.tb.fragment.Blank2Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_view)
    NavigationView mNav;

    private int checkedMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, Blank2Fragment.newInstance()).commit();

        mNav.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, Blank2Fragment.newInstance()).commit();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mNav.getMenu().findItem(checkedMenuItem).setChecked(false);
        checkedMenuItem = item.getItemId();
        mNav.getMenu().findItem(checkedMenuItem).setChecked(true);

        switch (item.getItemId()) {
            case R.id.info:

                return true;

            case R.id.order_list:

                return true;

            case R.id.message:

                return true;

            case R.id.notice:

                return true;

            case R.id.inquiry:

                return true;

            case R.id.setting:
                return true;

        }
        return false;
    }
}
