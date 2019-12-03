package com.basicmoon.expediaassessment.hotels;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.data.HotelsRepository;
import com.basicmoon.expediaassessment.data.source.HotelDataSource;
import com.basicmoon.expediaassessment.hotels.list.HotelsListFragment;
import com.basicmoon.expediaassessment.hotels.map.HotelsMapsFragment;
import com.basicmoon.expediaassessment.utils.ActivityUtils;
import com.basicmoon.expediaassessment.utils.ViewModelFactory;
import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class MapsActivity extends DaggerAppCompatActivity implements  HotelDataSource.LoadHotelsCallback {


    private List<Hotel> mHotelList;


    @Inject
    HotelsRepository mHotelsRepository;

    private HotelsViewModel mHotelsViewModel;


    private BootstrapButton mListViewButton;

    private BootstrapButton mMapViewButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mListViewButton = findViewById(R.id.list_button);
        mMapViewButton = findViewById(R.id.maps_button);


        mListViewButton.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener() {
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    HotelsListFragment hotelsListFragment = HotelsListFragment.newInstance(mHotelList);

                    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), hotelsListFragment, R.id.content);
                }
            }
        });


        mMapViewButton.setOnCheckedChangedListener(new BootstrapButton.OnCheckedChangedListener() {
            @Override
            public void OnCheckedChanged(BootstrapButton bootstrapButton, boolean isChecked) {
                if (isChecked) {
                    HotelsMapsFragment hotelsMapsFragment = HotelsMapsFragment.newInstance();
                    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), hotelsMapsFragment, R.id.content);
                }
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mHotelList = new ArrayList<>();
        mHotelsViewModel = obtainViewModel(this);

        mHotelsRepository.getHotels(this);

    }


    @Override
    public void onHotelsLoaded(List<Hotel> hotels) {
        mHotelList = hotels;

        mHotelsViewModel.getHotelsListLiveData().setValue(hotels);
        HotelsListFragment hotelsListFragment = HotelsListFragment.newInstance(mHotelList);

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), hotelsListFragment, R.id.content);
    }

    @Override
    public void onDataNotAvailable() {
        Timber.d("onDataNotAvailable");
    }

    public static HotelsViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(HotelsViewModel.class);
    }
}
