package com.basicmoon.expediaassessment.hotels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.basicmoon.expediaassessment.data.model.Hotel;

import java.util.List;

public class HotelsViewModel extends ViewModel {


    private MutableLiveData<String> title = new MutableLiveData<>("Title");

    private MutableLiveData<List<Hotel>> mHotelsListLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Hotel>> getHotelsListLiveData() {
        return mHotelsListLiveData;
    }

    public void setHotelsListLiveData(MutableLiveData<List<Hotel>> hotelsListLiveData) {
        mHotelsListLiveData = hotelsListLiveData;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public void loadData() {

    }
}
