package com.basicmoon.expediaassessment.di;

import com.basicmoon.expediaassessment.hotels.HotelsModule;
import com.basicmoon.expediaassessment.hotels.MapsActivity;
import com.basicmoon.expediaassessment.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {


    @ContributesAndroidInjector
    abstract SplashActivity bindsSplashActivity();

    @ContributesAndroidInjector(modules = HotelsModule.class)
    abstract MapsActivity bindsMapsActivity();

}
