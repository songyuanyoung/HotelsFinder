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


    /**
     * Query all the hotel from DataSource
     * @return a list of hotels
     */
    @Query("SELECT * FROM HOTEL")
    List<Hotel> getHotels();

    /**
     * Query hotel by name
     * @param hotelName
     * @return hotel data
     */
    @Query("SELECT * FROM hotel WHERE hotelName = :hotelName")
    Hotel findHotelByName(String hotelName);

    /**
     * delete the data in the table
     */
    @Query("DELETE FROM hotel")
    void deleteAllHotels();

}
