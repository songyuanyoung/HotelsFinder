package com.basicmoon.expediaassessment.hotels;

import com.basicmoon.expediaassessment.hotels.map.HotelsMapsFragment;
import com.basicmoon.expediaassessment.utils.LocationService;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HotelsModule {
    @ContributesAndroidInjector
    abstract HotelsMapsFragment bindsHotelMapFragment();

}
