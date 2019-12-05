package com.basicmoon.expediaassessment.data.source;

import androidx.annotation.NonNull;

import com.basicmoon.expediaassessment.data.db.HotelDao;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.utils.AppExecutors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocalHotelDataSource implements HotelDataSource {


    private final HotelDao mHotelDao;

    private final AppExecutors mAppExecutors;

    @Inject
    public LocalHotelDataSource(HotelDao hotelDao, AppExecutors appExecutors) {
        mHotelDao = hotelDao;
        mAppExecutors = appExecutors;
    }

    @Override
    public void getHotels(@NonNull LoadHotelsCallback loadHotelsCallback) {
       Runnable runnable = new Runnable() {
           @Override
           public void run() {
               final List<Hotel> hotels = mHotelDao.getHotels();
               mAppExecutors.getMainThreadExecutor().execute(new Runnable() {
                   @Override
                   public void run() {
                       if (hotels.isEmpty()) {
                           loadHotelsCallback.onDataNotAvailable();
                       } else {
                           loadHotelsCallback.onHotelsLoaded(hotels);
                       }
                   }
               });
           }
       };

       mAppExecutors.getIoExecutor().execute(runnable);

    }

    @Override
    public void findHotelByName(@NonNull String hotelName, @NonNull FindHotelByNameCallback findHotelByNameCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Hotel hotel = mHotelDao.findHotelByName(hotelName);
                mAppExecutors.getMainThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (hotel == null) {
                            findHotelByNameCallback.onHotelNotFoundByname();
                        } else {
                            findHotelByNameCallback.onHotelFoundByname(hotel);
                        }
                    }
                });
            }
        };

        mAppExecutors.getIoExecutor().execute(runnable);
    }

    @Override
    public void addHotel(Hotel hotel) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mHotelDao.insertHotel(hotel);
            }
        };

        mAppExecutors.getIoExecutor().execute(runnable);
    }

    @Override
    public void deleteAllHotels() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mHotelDao.deleteAllHotels();
            }
        };

        mAppExecutors.getIoExecutor().execute(runnable);
    }
}
