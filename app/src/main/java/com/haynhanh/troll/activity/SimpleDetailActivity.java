package com.haynhanh.troll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.haynhanh.troll.R;

public class SimpleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            bundle.get("item");
        }
    }
}
