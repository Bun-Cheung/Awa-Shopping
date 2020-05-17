package com.awareness.shopping;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.awareness.shopping.adapter.CouponAdapter;
import com.awareness.shopping.mockdata.CouponsData;

import java.util.List;


public class CouponActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        initView();
        fetchCouponsData();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.coupon_toolbar);
        toolbar.setTitle(R.string.coupons);
        toolbar.setTitleTextColor(getColor(R.color.colorBlack));
        setSupportActionBar(toolbar);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mRecyclerView = findViewById(R.id.recycle_view_coupons);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchCouponsData() {
        List<Integer> couponsList = CouponsData.getMockData();
        CouponAdapter adapter = new CouponAdapter(couponsList);
        adapter.setOnItemClickListener(position -> Toast.makeText(this, "get coupon success", Toast.LENGTH_SHORT).show());
        mRecyclerView.setAdapter(adapter);
    }
}
