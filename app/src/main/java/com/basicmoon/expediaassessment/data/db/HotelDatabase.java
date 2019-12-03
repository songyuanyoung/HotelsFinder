package com.basicmoon.expediaassessment.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.basicmoon.expediaassessment.data.db.HotelDao;
import com.basicmoon.expediaassessment.data.model.Hotel;


@Database(entities = {Hotel.class}, version = 1, exportSchema = false)
public abstract class HotelDatabase extends RoomDatabase {


    public abstract HotelDao mHotelDao();

}
