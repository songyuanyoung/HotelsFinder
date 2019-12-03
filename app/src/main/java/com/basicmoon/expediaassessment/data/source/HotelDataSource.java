package com.basicmoon.expediaassessment.data.source;

import androidx.annotation.NonNull;

import com.basicmoon.expediaassessment.data.model.Hotel;

import java.util.List;

public interface HotelDataSource {

    interface LoadHotelsCallback {

        void onHotelsLoaded(List<Hotel> hotels);

        void onDataNotAvailable();
    }

    void getHotels(@NonNull LoadHotelsCallback loadHotelsCallback);

    void addHotel(Hotel hotel);

    void deleteAllHotels();
}
