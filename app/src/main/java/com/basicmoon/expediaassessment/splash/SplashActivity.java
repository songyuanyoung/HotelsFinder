package com.basicmoon.expediaassessment.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.HotelsRepository;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.data.source.HotelDataSource;
import com.basicmoon.expediaassessment.hotels.MapsActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import timber.log.Timber;

public class SplashActivity extends DaggerAppCompatActivity implements HotelDataSource.LoadHotelsCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private static final String[] PERMISSION = {Manifest.permission.ACCESS_FINE_LOCATION};

    @Inject
    HotelsRepository mHotelsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (hasLocationPermission()) {
            fetchHotelDataFromRemote();
        } else {
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.str_request_Location_permission),
                    LOCATION_PERMISSION_REQUEST_CODE,
                    PERMISSION
                    );
        }

    }

    private boolean hasLocationPermission() {
        return EasyPermissions.hasPermissions(this, PERMISSION);
    }

    @AfterPermissionGranted(LOCATION_PERMISSION_REQUEST_CODE)
    private void fetchHotelDataFromRemote() {
        mHotelsRepository.getHotelsFromRemote(this);
    }


    @Override
    public void onHotelsLoaded(List<Hotel> hotels) {

        Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDataNotAvailable() {

        Timber.d("onDataNotAvailable");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}

