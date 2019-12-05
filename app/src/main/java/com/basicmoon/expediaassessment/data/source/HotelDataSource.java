package com.basicmoon.expediaassessment.data.source;

import androidx.annotation.NonNull;

import com.basicmoon.expediaassessment.data.model.Hotel;

import java.util.List;


/**
 * an interface that defines a set of operations
 * on data
 */
public interface HotelDataSource {

    interface LoadHotelsCallback {

        void onHotelsLoaded(List<Hotel> hotels);

        void onDataNotAvailable();
    }

    interface FindHotelByNameCallback {

        void onHotelFoundByName(Hotel hotel);

        void onHotelNotFoundByName();
    }

    void getHotels(@NonNull LoadHotelsCallback loadHotelsCallback);

    void findHotelByName(@NonNull String hotelName, @NonNull FindHotelByNameCallback findHotelByNameCallback);

    void addHotel(Hotel hotel);

    void deleteAllHotels();
}
