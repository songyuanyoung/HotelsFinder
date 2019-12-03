package com.basicmoon.expediaassessment.data.source;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.data.model.HotelsResponse;
import com.basicmoon.expediaassessment.hotels.HotelsService;
import com.basicmoon.expediaassessment.hotels.MapsActivity;
import com.basicmoon.expediaassessment.splash.SplashActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;
import retrofit2.Retrofit;
import timber.log.Timber;

@Singleton
public class RemoteHotelDataSource implements HotelDataSource {

    Retrofit mRetrofit;


    @Inject
    public RemoteHotelDataSource(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public void getHotels(@NonNull LoadHotelsCallback loadHotelsCallback) {


        mRetrofit.create(HotelsService.class)
                .getNearByBikes()
                .toFlowable(BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<HotelsResponse>() {
                    @Override
                    public void onNext(HotelsResponse hotelsResponse)
                    {
                        loadHotelsCallback.onHotelsLoaded(hotelsResponse.getHotels());
                    }

                    @Override
                    public void onError(Throwable t) {
                        loadHotelsCallback.onDataNotAvailable();
                    }

                    @Override
                    public void onComplete() {


                    }
                });



    }

    @Override
    public void addHotel(Hotel hotel) {

    }

    @Override
    public void deleteAllHotels() {

    }
}
