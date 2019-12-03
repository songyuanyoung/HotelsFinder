package com.basicmoon.expediaassessment.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationService {



    private OnGetLocationListener onGetLocationListener;

    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;

    private Context context;

    public void setOnGetLocationListener(OnGetLocationListener onGetLocationListener) {
        this.onGetLocationListener = onGetLocationListener;
    }

    public void init(Context context) {
        this.context = context;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

    }


    public void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location == null) {
                    return;
                }

                onGetLocationListener.onGetLocation(location);
            }
        });

    }

    public void unregisterListener() {

        if (onGetLocationListener != null)
            onGetLocationListener = null;

    }

    public interface OnGetLocationListener {
        void onGetLocation(Location location);
    }
}
