package com.basicmoon.expediaassessment.di;

import com.basicmoon.expediaassessment.details.HotelDetailsActivity;
import com.basicmoon.expediaassessment.hotels.HotelsModule;
import com.basicmoon.expediaassessment.hotels.HotelsMainActivity;
import com.basicmoon.expediaassessment.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {


    @ActivityScoped
    @ContributesAndroidInjector
    abstract SplashActivity bindsSplashActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = HotelsModule.class)
    abstract HotelsMainActivity bindsMapsActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract HotelDetailsActivity bindsHotelDetailsActivity();

}
