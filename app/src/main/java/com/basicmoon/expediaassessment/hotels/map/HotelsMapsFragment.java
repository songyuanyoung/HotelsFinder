package com.basicmoon.expediaassessment.hotels.map;


import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.HotelsRepository;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.hotels.HotelsMainActivity;
import com.basicmoon.expediaassessment.hotels.HotelsViewModel;
import com.basicmoon.expediaassessment.utils.LocationService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelsMapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelsMapsFragment extends DaggerFragment implements OnMapReadyCallback,
        LocationService.OnGetLocationListener,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    private HotelsViewModel mHotelsViewModel;

    @Inject
    LocationService mLocationService;

    @Inject
    HotelsRepository mHotelsRepository;

    private Location mCurrentLocation;

    @Inject
    public HotelsMapsFragment() {
        // Required empty public constructor
    }

    public static HotelsMapsFragment newInstance() {
        HotelsMapsFragment fragment = new HotelsMapsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        mLocationService.init(getActivity());
        mLocationService.init(getActivity().getApplicationContext());
        mLocationService.setOnGetLocationListener(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mHotelsViewModel = HotelsMainActivity.obtainViewModel(getActivity());

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Set up maps style for google maps
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.google_maps));

            if (!success) {
                Timber.d( "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Timber.d("Can't find style. Error: " + e);
        }

        mLocationService.startLocationUpdates();

        mMap.setMyLocationEnabled(true);
        mMap.setOnInfoWindowClickListener(this);

        if (mCurrentLocation != null) {
            LatLng currentLocationLatLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocationLatLng, 15));
        } else {
            mLocationService.startLocationUpdates();
        }

        List<Hotel> hotelList = mHotelsViewModel.getHotelsListLiveData().getValue();

        for (Hotel hotel : hotelList) {
            LatLng latLng = new LatLng(hotel.getLatitude(), hotel.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(hotel.getHotelName()).icon(getMarkerIcon("#2196F3")));
        }

    }


    public BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }



    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationService.unregisterListener();
    }

    @Override
    public void onGetLocation(Location location) {
        if (location != null) {
            mCurrentLocation = location;
            Timber.d(mCurrentLocation.toString());
            LatLng currentLocationLatLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocationLatLng, 15));
        }
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Timber.d(marker.getTitle());

        mHotelsViewModel.openHotelDetails(marker.getTitle());

    }
}
