package com.basicmoon.expediaassessment.hotels;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.HotelsRepository;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.data.source.HotelDataSource;
import com.basicmoon.expediaassessment.details.HotelDetailsActivity;
import com.basicmoon.expediaassessment.hotels.list.HotelsListFragment;
import com.basicmoon.expediaassessment.hotels.list.SortType;
import com.basicmoon.expediaassessment.hotels.map.HotelsMapsFragment;
import com.basicmoon.expediaassessment.utils.ActivityUtils;
import com.basicmoon.expediaassessment.utils.Const;
import com.basicmoon.expediaassessment.utils.Event;
import com.basicmoon.expediaassessment.utils.ViewModelFactory;
import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class HotelsMainActivity extends DaggerAppCompatActivity implements  HotelDataSource.LoadHotelsCallback {


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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mHotelList = new ArrayList<>();
        mHotelsViewModel = obtainViewModel(this);

        mHotelsRepository.getHotels(this);

        mHotelsViewModel.getOpenHotelDetailsEvent().observe(this, new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> hotelName) {
                openHotelDetails(hotelName);
            }
        });

    }

    private void openHotelDetails(Event<String> hotelName) {

        Intent intent = new Intent(HotelsMainActivity.this, HotelDetailsActivity.class);
        intent.putExtra(Const.BUNDLE_HOTEL_NAME, hotelName.getEventContent());
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_sort:
                showSortPopUpMenu();
                break;
        }

        return true;
    }

    private void showSortPopUpMenu() {
        PopupMenu popup = new PopupMenu(this, findViewById(R.id.menu_sort));
        popup.getMenuInflater().inflate(R.menu.sort_options_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sort_by_name:
                        mHotelsViewModel.setSortType(SortType.SORT_BY_NAME);
                        break;
                    case R.id.sort_by_price:
                        mHotelsViewModel.setSortType(SortType.SORT_BY_PRICE);
                        break;
                    case R.id.sort_by_rate:
                        mHotelsViewModel.setSortType(SortType.SORT_BY_RATE);
                        break;
                }
//                mTasksViewModel.loadTasks(false);
                return true;
            }
        });

        popup.show();
    }


    @Override
    public void onHotelsLoaded(List<Hotel> hotels) {
        mHotelList = hotels;

        mHotelsViewModel.getHotelsListLiveData().setValue(hotels);
        HotelsListFragment hotelsListFragment = HotelsListFragment.newInstance(mHotelList);

        Timber.d(hotels.get(0).toString());

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), hotelsListFragment, R.id.content);
    }

    @Override
    public void onDataNotAvailable() {
        Timber.d("onDataNotAvailable");

        Toast.makeText(this, getText(R.string.str_error_message), Toast.LENGTH_LONG).show();
    }

    public static HotelsViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(HotelsViewModel.class);
    }



}
