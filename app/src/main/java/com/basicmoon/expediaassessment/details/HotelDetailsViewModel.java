package com.basicmoon.expediaassessment.details;

import android.text.TextUtils;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.basicmoon.expediaassessment.data.model.Hotel;

public class HotelDetailsViewModel extends ViewModel {

    private MutableLiveData<Hotel> mHotelMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<String> mRatingMutableLiveData = new MutableLiveData<>();

    private LiveData<Boolean> mIsDiscountMessageEmpty = Transformations.map(mHotelMutableLiveData, new Function<Hotel, Boolean>() {
        @Override
        public Boolean apply(Hotel input) {
            return TextUtils.isEmpty(input.getDiscountMessage());
        }
    });


    public LiveData<Boolean> getIsDiscountMessageEmpty() {
        return mIsDiscountMessageEmpty;
    }

    public MutableLiveData<Hotel> getHotelMutableLiveData() {
        return mHotelMutableLiveData;
    }


    public MutableLiveData<String> getRatingMutableLiveData() {
        return mRatingMutableLiveData;
    }
}
