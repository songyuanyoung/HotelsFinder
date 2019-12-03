package com.basicmoon.expediaassessment.data.di;

import androidx.room.Room;

import com.basicmoon.expediaassessment.base.ExpediaApplication;
import com.basicmoon.expediaassessment.data.db.HotelDao;
import com.basicmoon.expediaassessment.data.db.HotelDatabase;
import com.basicmoon.expediaassessment.data.source.HotelDataSource;
import com.basicmoon.expediaassessment.data.source.LocalHotelDataSource;
import com.basicmoon.expediaassessment.data.source.RemoteHotelDataSource;
import com.basicmoon.expediaassessment.di.Local;
import com.basicmoon.expediaassessment.di.Remote;
import com.basicmoon.expediaassessment.utils.AppExecutors;
import com.basicmoon.expediaassessment.utils.Const;
import com.basicmoon.expediaassessment.utils.DiskIOThreadExecutor;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HotelsRepositoryModule {

    private static final int THREAD_COUNT = 3;




    @Singleton
    @Provides
    @Local
    HotelDataSource providesLocalHotelDataSource(HotelDao hotelDao, AppExecutors appExecutors) {
        return new LocalHotelDataSource(hotelDao, appExecutors);
    }

    @Singleton
    @Provides
    @Remote
    HotelDataSource providesRemoteHotelDataSource(Retrofit retrofit) {
        return new RemoteHotelDataSource(retrofit);
    }

    @Singleton
    @Provides
    HotelDao providesHotelDao(HotelDatabase hotelDataBase) {
        return hotelDataBase.mHotelDao();
    }

    @Singleton
    @Provides
    HotelDatabase providesHotelsDatabase(ExpediaApplication application) {
        return Room.databaseBuilder(application.getApplicationContext(), HotelDatabase.class, Const.DATABASE_NAME).build();    }

    @Singleton
    @Provides
    AppExecutors providesAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT), new AppExecutors.MainThreadExecutor());
    }

}
