package com.basicmoon.expediaassessment.base;

import com.basicmoon.expediaassessment.BuildConfig;
import com.basicmoon.expediaassessment.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class ExpediaApplication extends DaggerApplication {

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
