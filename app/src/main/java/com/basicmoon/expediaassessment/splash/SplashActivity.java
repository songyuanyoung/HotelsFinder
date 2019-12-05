package com.basicmoon.expediaassessment.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.HotelsRepository;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.data.source.HotelDataSource;
import com.basicmoon.expediaassessment.hotels.HotelsMainActivity;
import com.squareup.picasso.Picasso;

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

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mImageView = (ImageView) findViewById(R.id.splash_imageview);

        Picasso.get().load(R.drawable.splash_image).into(mImageView);

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

        Intent intent = new Intent(SplashActivity.this, HotelsMainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDataNotAvailable() {

        Timber.d("onDataNotAvailable");

        Toast.makeText(this, getText(R.string.str_error_message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}

