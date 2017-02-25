package com.haynhanh.troll;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import com.haynhanh.troll.adapter.DrawerAdapter;
import com.haynhanh.troll.model.Domain;
import com.haynhanh.troll.network.ApiUtil;
import com.haynhanh.troll.network.MyApiEndpointInterface;
import com.haynhanh.troll.network.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MyApiEndpointInterface apiEndpointInterface = null;


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (NetworkHelper.isOnline()) {
            apiEndpointInterface = ApiUtil.getApiEndpointInterface();
            apiEndpointInterface.getDomain().enqueue(new Callback<Domain>() {
                @Override
                public void onResponse(Call<Domain> call, Response<Domain> response) {
                    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                    mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, 0, 0);
                    mDrawerLayout.setDrawerListener(mDrawerToggle);

                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setHomeButtonEnabled(true);

                    mDrawerList = (ListView) findViewById(R.id.left_drawer);
                    mDrawerList.setAdapter(new DrawerAdapter(MainActivity.this, response.body().getData()));
                }

                @Override
                public void onFailure(Call<Domain> call, Throwable t) {}
            });
        } else
            Toast.makeText(this, "No network connection", Toast.LENGTH_SHORT).show();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        if (mDrawerToggle != null)
            mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
