package com.haynhanh.troll;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.haynhanh.troll.adapter.DrawerAdapter;
import com.haynhanh.troll.model.domain.Domain;
import com.haynhanh.troll.model.domain.DomainDetail;
import com.haynhanh.troll.model.item.Item;
import com.haynhanh.troll.model.item.ItemDetail;
import com.haynhanh.troll.network.ApiUtil;
import com.haynhanh.troll.network.MyApi;
import com.haynhanh.troll.network.NetworkHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MyApi api = null;

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private DrawerAdapter drawerAdapter;
    private List<DomainDetail> domainDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerList = (ListView) findViewById(R.id.left_drawer);
        domainDetailList = new ArrayList<>();

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Mao " + domainDetailList.get(position).getName(), Toast.LENGTH_SHORT).show();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (NetworkHelper.isOnline()) {
            api = ApiUtil.getApi();
            api.getDomainList().enqueue(new Callback<Domain>() {
                @Override
                public void onResponse(Call<Domain> call, Response<Domain> response) {
                    domainDetailList = response.body().getData();
                    drawerAdapter = new DrawerAdapter(MainActivity.this, domainDetailList);
                    drawerList.setAdapter(drawerAdapter);
                }

                @Override
                public void onFailure(Call<Domain> call, Throwable t) {}
            });
        } else {
            Toast.makeText(this, "No network connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickHomeButton(View view) {
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void clickListViewButton(View view) {
        Toast.makeText(this, "List View", Toast.LENGTH_SHORT).show();

        Map<String, Object> params = new HashMap<>();
        params.put("domain_id", 1);
        params.put("greater_id", 0);
        params.put("less_id", 1000);
        params.put("limit", 10);
        params.put("mid_id", 2);
        api = ApiUtil.getApi();
        api.getItemList(params).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Log.e("Mao", "Mao " + response.body().getData());
                List<ItemDetail> itemDetailList = new ArrayList<ItemDetail>();
                itemDetailList = response.body().getData();
                Log.e("Mao", "Mao " + itemDetailList.get(0).getTitle());
                Log.e("Mao", "Mao " + itemDetailList.get(0).getParts().get(0).getUrl());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    public void clickGridViewButton(View view) {
        Toast.makeText(this, "Grid View", Toast.LENGTH_SHORT).show();
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

}
