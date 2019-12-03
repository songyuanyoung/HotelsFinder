package com.basicmoon.expediaassessment.hotels.map;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.HotelsRepository;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.hotels.HotelsViewModel;
import com.basicmoon.expediaassessment.hotels.MapsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelsMapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelsMapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    private HotelsViewModel mHotelsViewModel;


    @Inject
    HotelsRepository mHotelsRepository;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mHotelsViewModel = MapsActivity.obtainViewModel(getActivity());

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        List<Hotel> hotelList = mHotelsViewModel.getHotelsListLiveData().getValue();
        for (Hotel hotel : hotelList) {
            LatLng latLng = new LatLng(Double.valueOf(hotel.getLatitude()), Double.valueOf(hotel.getLongitude()));
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
        }

        LatLng chicago = new LatLng(41.8781, -87.6298);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chicago, 15));
    }
}
