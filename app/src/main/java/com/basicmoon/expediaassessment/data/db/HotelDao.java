package com.basicmoon.expediaassessment.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.basicmoon.expediaassessment.data.model.Hotel;

import java.util.List;

@Dao
public interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertHotel(Hotel hotel);

    @Query("SELECT * FROM HOTEL")
    List<Hotel> getHotels();

    @Query("DELETE FROM hotel")
    void deleteAllHotels();

}
