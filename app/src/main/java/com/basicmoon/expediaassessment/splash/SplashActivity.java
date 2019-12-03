package com.basicmoon.expediaassessment.splash;

import android.content.Intent;
import android.os.Bundle;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.HotelsRepository;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.data.model.HotelsResponse;
import com.basicmoon.expediaassessment.data.source.HotelDataSource;
import com.basicmoon.expediaassessment.data.source.RemoteHotelDataSource;
import com.basicmoon.expediaassessment.hotels.HotelsService;
import com.basicmoon.expediaassessment.hotels.MapsActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.BackpressureStrategy;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;
import retrofit2.Retrofit;
import timber.log.Timber;

public class SplashActivity extends DaggerAppCompatActivity implements HotelDataSource.LoadHotelsCallback {

    @Inject
    HotelsRepository mHotelsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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
}

