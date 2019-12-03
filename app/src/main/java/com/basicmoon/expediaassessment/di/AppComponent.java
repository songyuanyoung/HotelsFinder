package com.basicmoon.expediaassessment.di;

import javax.inject.Singleton;

import com.basicmoon.expediaassessment.base.ExpediaApplication;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import com.basicmoon.expediaassessment.data.di.HotelsRepositoryModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        HotelsRepositoryModule.class
})
public interface AppComponent extends AndroidInjector<ExpediaApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder onApplicationCreated(ExpediaApplication application);

        AppComponent build();
    }
}
