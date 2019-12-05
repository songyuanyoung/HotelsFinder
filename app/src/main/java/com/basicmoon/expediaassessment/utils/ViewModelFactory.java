package com.basicmoon.expediaassessment.utils;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.basicmoon.expediaassessment.details.HotelDetailsViewModel;
import com.basicmoon.expediaassessment.hotels.HotelsViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HotelsViewModel.class)) {
            //noinspection unchecked
            return (T) new HotelsViewModel();
        } else if (modelClass.isAssignableFrom(HotelDetailsViewModel.class)) {
            return (T) new HotelDetailsViewModel();
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
