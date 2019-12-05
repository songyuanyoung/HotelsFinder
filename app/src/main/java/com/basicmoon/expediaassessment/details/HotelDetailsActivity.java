package com.basicmoon.expediaassessment.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.HotelsRepository;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.data.source.HotelDataSource;
import com.basicmoon.expediaassessment.utils.ActivityUtils;
import com.basicmoon.expediaassessment.utils.Const;
import com.basicmoon.expediaassessment.utils.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

/**
 * Hosting activity for hotel details.
 */
public class HotelDetailsActivity extends DaggerAppCompatActivity implements HotelDataSource.FindHotelByNameCallback {

    private String mHotelname;

    @Inject
    HotelsRepository mHotelsRepository;

    private HotelDetailsViewModel mHotelDetailsViewModel;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            mHotelname = getIntent().getStringExtra(Const.BUNDLE_HOTEL_NAME);
        }

        setContentView(R.layout.activity_hotel_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView) findViewById(R.id.hotel_detail_imageView);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mHotelname);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        HotelDetailsFragment hotelDetailsFragment = HotelDetailsFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), hotelDetailsFragment, R.id.content);


        mHotelsRepository.findHotelByName(mHotelname, this);

        mHotelDetailsViewModel = obtainViewModel(this);

    }

    /**
     * Setting up back button.
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onHotelFoundByName(Hotel hotel) {
        Timber.d(hotel.toString());
        Picasso.get().load(hotel.getHotelImageURL()).into(mImageView);

        mHotelDetailsViewModel.getHotelMutableLiveData().setValue(hotel);
    }

    @Override
    public void onHotelNotFoundByName() {
        Timber.d("onHotelNotFoundByName");

        Toast.makeText(this, getText(R.string.str_error_message), Toast.LENGTH_LONG).show();
    }

    public static HotelDetailsViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(HotelDetailsViewModel.class);
    }
}
