package com.haynhanh.troll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.haynhanh.troll.R;
import com.haynhanh.troll.adapter.ItemAdapter;
import com.haynhanh.troll.adapter.SlidemenuAdapter;
import com.haynhanh.troll.model.domain.Domain;
import com.haynhanh.troll.model.domain.DomainDetail;
import com.haynhanh.troll.model.item.Item;
import com.haynhanh.troll.model.item.ItemDetail;
import com.haynhanh.troll.model.item.ItemView;
import com.haynhanh.troll.network.ApiUtil;
import com.haynhanh.troll.network.MyApi;
import com.haynhanh.troll.network.NetworkHelper;

import java.io.Serializable;
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
    private SlidemenuAdapter drawerAdapter;
    private List<DomainDetail> domainDetailList;

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<ItemView> itemViewList;

    private ImageView imageView;
    private TextView textView;

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
//                Toast.makeText(MainActivity.this, "Mao " + domainDetailList.get(position).getName(), Toast.LENGTH_SHORT).show();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                showItemList(domainDetailList.get(position).getId());
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
                    drawerAdapter = new SlidemenuAdapter(MainActivity.this, domainDetailList);
                    drawerList.setAdapter(drawerAdapter);

                    showItemList(domainDetailList.get(0).getId());
                }

                @Override
                public void onFailure(Call<Domain> call, Throwable t) {
                }
            });
        } else {
            Toast.makeText(this, "No network connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void showItemList(int domain_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("domain_id", domain_id);
//        params.put("greater_id", 0);
//        params.put("less_id", 1000);
//        params.put("limit", 10);
//        params.put("mid_id", 2);
        api = ApiUtil.getApi();
        api.getItemList(params).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                List<ItemDetail> itemDetailList = response.body().getData();
                itemViewList = new ArrayList<>();
                for (int i = 0; i < itemDetailList.size(); i++) {
                    String name = itemDetailList.get(i).getTitle();
                    String image = itemDetailList.get(i).getParts().get(0).getUrl();
                    ItemView itemView = new ItemView(name, image);
                    itemViewList.add(itemView);
                }
                showRecyclerView();
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    private void showRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        itemAdapter = new ItemAdapter(this, itemViewList, new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemView itemView) {
//                Toast.makeText(MainActivity.this, "Mao " + itemView.getName(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(MainActivity.this, SimpleDetailActivity.class);
                intent.putExtra("item", (Serializable) itemView);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(itemAdapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    public void clickHomeButton(View view) {
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void clickListViewButton(View view) {
        Toast.makeText(this, "List View", Toast.LENGTH_SHORT).show();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void clickGridViewButton(View view) {
        Toast.makeText(this, "Grid View", Toast.LENGTH_SHORT).show();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
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
