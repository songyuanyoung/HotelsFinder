package com.basicmoon.expediaassessment.data.source;

import androidx.annotation.NonNull;

import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.data.model.HotelsResponse;
import com.basicmoon.expediaassessment.hotels.data.HotelsService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * A remote data souce
 * that fetches date from service using Retrofit
 *
 */
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
                        Timber.d(t.getMessage());
                        loadHotelsCallback.onDataNotAvailable();
                    }

                    @Override
                    public void onComplete() {


                    }
                });



    }

    @Override
    public void findHotelByName(@NonNull String hotelName, @NonNull FindHotelByNameCallback findHotelByNameCallback) {

    }


    @Override
    public void addHotel(Hotel hotel) {

    }

    @Override
    public void deleteAllHotels() {

    }
}
