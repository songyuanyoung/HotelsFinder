package com.basicmoon.expediaassessment.data;


import androidx.annotation.NonNull;

import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.data.source.HotelDataSource;
import com.basicmoon.expediaassessment.di.Local;
import com.basicmoon.expediaassessment.di.Remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import timber.log.Timber;

@Singleton
public class HotelsRepository implements HotelDataSource {

    private HotelDataSource mLocalHotelDataSource;

    private HotelDataSource mRemoteHotelDataSource;

    @Inject
    Retrofit mRetrofit;

    @Inject
    public HotelsRepository(@Local HotelDataSource localHotelDataSource, @Remote HotelDataSource remoteDataSource) {

        mLocalHotelDataSource = localHotelDataSource;
        mRemoteHotelDataSource = remoteDataSource;
    }

    @Override
    public void getHotels(@NonNull LoadHotelsCallback loadHotelsCallback) {
        mLocalHotelDataSource.getHotels(new LoadHotelsCallback() {
            @Override
            public void onHotelsLoaded(List<Hotel> hotels) {
                loadHotelsCallback.onHotelsLoaded(hotels);
            }

            @Override
            public void onDataNotAvailable() {
                loadHotelsCallback.onDataNotAvailable();
            }
        });
    }


    public void getHotelsFromRemote(LoadHotelsCallback loadHotelsCallback) {

        mRemoteHotelDataSource.getHotels(new LoadHotelsCallback() {
            @Override
            public void onHotelsLoaded(List<Hotel> hotels) {
                for (int i = 0; i < hotels.size(); i++) {
                    mLocalHotelDataSource.addHotel(hotels.get(i));
                }
                loadHotelsCallback.onHotelsLoaded(hotels);
            }

            @Override
            public void onDataNotAvailable() {
                loadHotelsCallback.onDataNotAvailable();
                Timber.d("onDataNotAvailable");
            }
        });
    }


    @Override
    public void addHotel(Hotel hotel) {
        mLocalHotelDataSource.addHotel(hotel);
    }

    @Override
    public void deleteAllHotels() {
        mLocalHotelDataSource.deleteAllHotels();
    }

}
