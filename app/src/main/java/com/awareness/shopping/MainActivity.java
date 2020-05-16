package com.awareness.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.awareness.shopping.adapter.MerchantAdapter;
import com.awareness.shopping.data.MerchantsData;
import com.awareness.shopping.data.WeatherDescription;
import com.awareness.shopping.entity.Merchant;
import com.huawei.hms.kit.awareness.Awareness;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hms.kit.awareness.status.WeatherStatus;
import com.huawei.hms.kit.awareness.status.weather.Situation;
import com.huawei.hms.kit.awareness.status.weather.WeatherSituation;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();
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

        mTemperature = findViewById(R.id.temperature);
        mCity = findViewById(R.id.city);
        mWeather = findViewById(R.id.weather);

        List<Merchant> merchantList = MerchantsData.getData();
        RecyclerView recyclerView = findViewById(R.id.recycle_view_merchants);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MerchantAdapter(merchantList));
    }

    private void requestPermission() {
        String[] permissions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
        } else {
            permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean permissionGranted = true;
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int result:grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) {
                    permissionGranted = false;
                    break;
                }
            }
            if (permissionGranted) {
                getWeather();
            }
            else {
                Toast.makeText(this,"grant permission failed",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getWeather() {
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
                .addOnFailureListener(e -> {
                    Log.e(TAG, "get weather failed", e);
                });
    }
}
