package com.basicmoon.expediaassessment.base;

import androidx.annotation.VisibleForTesting;

import com.basicmoon.expediaassessment.BuildConfig;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

import com.basicmoon.expediaassessment.data.HotelsRepository;
import com.basicmoon.expediaassessment.data.source.LocalHotelDataSource;
import com.basicmoon.expediaassessment.di.DaggerAppComponent;

import javax.inject.Inject;

import timber.log.Timber;

public class ExpediaApplication extends DaggerApplication {
//
//
//    @Inject
//    HotelsRepository mHotelsRepository;


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().onApplicationCreated(this).build();
    }

}
