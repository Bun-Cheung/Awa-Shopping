package com.awareness.shopping;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.awareness.shopping.adapter.MerchantAdapter;
import com.awareness.shopping.data.MerchantsData;
import com.awareness.shopping.entity.Merchant;
import com.huawei.hms.kit.awareness.Awareness;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();
    private List<Merchant> merchantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        querySupportingCapabilities();
        String content = "Multiple coupons in O'PLAZA!";
        Utils.sendNotification(this, content);
    }

    private void querySupportingCapabilities() {
        Awareness.getCaptureClient(this).querySupportingCapabilities()
                .addOnSuccessListener(capabilityResponse -> {
                    CapabilityStatus status = capabilityResponse.getCapabilityStatus();
                    status.getCapabilities();
                    Log.i(TAG, "get supporting capabilities success");
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "get supporting capabilities failed");
                    e.printStackTrace();
                });
    }

    private void initView() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        merchantList = MerchantsData.getData();
        RecyclerView recyclerView = findViewById(R.id.recycle_view_merchants);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MerchantAdapter(merchantList));
    }
}
