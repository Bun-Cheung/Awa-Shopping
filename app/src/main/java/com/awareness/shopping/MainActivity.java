package com.awareness.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.awareness.shopping.adapter.MerchantAdapter;
import com.awareness.shopping.entity.ShoppingMall;
import com.awareness.shopping.mockdata.MerchantsData;
import com.awareness.shopping.entity.Merchant;
import com.awareness.shopping.mockdata.ShoppingMallData;
import com.huawei.hms.kit.awareness.Awareness;
import com.huawei.hms.kit.awareness.barrier.AwarenessBarrier;
import com.huawei.hms.kit.awareness.barrier.BarrierUpdateRequest;
import com.huawei.hms.kit.awareness.barrier.BehaviorBarrier;
import com.huawei.hms.kit.awareness.barrier.LocationBarrier;
import com.huawei.hms.kit.awareness.barrier.TimeBarrier;
import com.huawei.hms.kit.awareness.status.WeatherStatus;
import com.huawei.hms.kit.awareness.status.weather.Situation;
import com.huawei.hms.kit.awareness.status.weather.WeatherSituation;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String COMBINED_BARRIER_LABEL = "shoppingMall_A combined label";
    private final String TAG = getClass().getSimpleName();
    private static final int PERMISSION_REQUEST_CODE = 940;
    private TextView mTemperature;
    private TextView mCity;
    private TextView mWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        requestPermission();
    }

    private void initView() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mTemperature = findViewById(R.id.temperature);
        mCity = findViewById(R.id.city);
        mWeather = findViewById(R.id.weather);

        List<Merchant> merchantList = MerchantsData.getMockData();
        RecyclerView recyclerView = findViewById(R.id.recycle_view_merchants);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        MerchantAdapter adapter = new MerchantAdapter(merchantList);
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(this, CouponActivity.class);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    private void requestPermission() {
        String[] permissions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION, Manifest.permission.ACTIVITY_RECOGNITION};
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
        } else {
            permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    "com.huawei.hms.permission.ACTIVITY_RECOGNITION"};
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean permissionHasGranted = true;
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) {
                    permissionHasGranted = false;
                    break;
                }
            }
            if (permissionHasGranted) {
                addBarrierToAwarenessKit();
                getWeatherFromAwarenessKit();
            } else {
                Toast.makeText(this, "grant permission failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addBarrierToAwarenessKit() {
        ShoppingMall shoppingMall = ShoppingMallData.getMockData().get(0);
        long timeOfDuration = 10 * 1000L;
        AwarenessBarrier stayInShoppingMall = LocationBarrier.stay(shoppingMall.getLatitude(), shoppingMall.getLongitude(),
                shoppingMall.getRadius(), timeOfDuration);
        AwarenessBarrier onBicycleOrInVehicle = BehaviorBarrier.keeping(BehaviorBarrier.BEHAVIOR_ON_BICYCLE,
                BehaviorBarrier.BEHAVIOR_IN_VEHICLE);
        AwarenessBarrier atNight = TimeBarrier.inTimeCategory(TimeBarrier.TIME_CATEGORY_NIGHT);
        AwarenessBarrier combinedBarrier = AwarenessBarrier.and(stayInShoppingMall,
                AwarenessBarrier.not(onBicycleOrInVehicle), AwarenessBarrier.not(atNight));

        PendingIntent pendingIntent;
        Intent intent = new Intent(this, BarrierService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pendingIntent = PendingIntent.getForegroundService(this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            pendingIntent = PendingIntent.getService(this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
        }
        addBarrier(COMBINED_BARRIER_LABEL, combinedBarrier, pendingIntent);
    }

    private void addBarrier(String label, AwarenessBarrier barrier, PendingIntent pendingIntent) {
        BarrierUpdateRequest request = new BarrierUpdateRequest.Builder()
                .addBarrier(label, barrier, pendingIntent)
                .build();

        Awareness.getBarrierClient(this).updateBarriers(request)
                .addOnSuccessListener(aVoid -> {
                    Log.i(TAG, "add barrier success");
                    Toast.makeText(this, "add barrier success", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "add barrier failed", e);
                    Toast.makeText(this, "add barrier failed", Toast.LENGTH_SHORT).show();
                });
    }

    private void getWeatherFromAwarenessKit() {
        Awareness.getCaptureClient(this).getWeatherByDevice()
                .addOnSuccessListener(weatherStatusResponse -> {
                    WeatherStatus weatherStatus = weatherStatusResponse.getWeatherStatus();
                    WeatherSituation weatherSituation = weatherStatus.getWeatherSituation();
                    Situation situation = weatherSituation.getSituation();
                    mTemperature.setText(String.valueOf(situation.getTemperatureC()));
                    mCity.setText(weatherSituation.getCity().getName());
                    int weatherId = situation.getWeatherId();
                    int cnWeatherId = situation.getCnWeatherId();
                    String weatherDesc = WeatherDescription.getWeatherDescMap().get(weatherId);
                    if (weatherDesc != null) {
                        mWeather.setText(weatherDesc);
                    } else {
                        weatherDesc = WeatherDescription.getCnWeatherDescMap().get(cnWeatherId);
                        mWeather.setText(weatherDesc);
                    }
                })
                .addOnFailureListener(e -> Log.e(TAG, "get weather failed", e));
    }
}
