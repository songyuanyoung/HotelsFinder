package com.basicmoon.expediaassessment.hotels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.hotels.list.SortType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HotelsViewModel extends ViewModel {

    private SortType mSortType = SortType.SORT_BY_RATE;

    private MutableLiveData<List<Hotel>> mHotelsListLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Hotel>> getHotelsListLiveData() {
        return mHotelsListLiveData;
    }

    public void setHotelsListLiveData(MutableLiveData<List<Hotel>> hotelsListLiveData) {
        mHotelsListLiveData = hotelsListLiveData;
    }

    public void setSortType (SortType sortType) {
        mSortType = sortType;

        switch (sortType) {
            case SORT_BY_NAME:
                sortHotelsByName();
                break;
            case SORT_BY_PRICE:
                sortHotelByPrice();
                break;
            case SORT_BY_RATE:
                sortHotelsByRate();
                break;
        }
    }

    private void sortHotelsByName() {

        List<Hotel> list = mHotelsListLiveData.getValue();
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel, Hotel t1) {
                return hotel.getHotelName().compareTo(t1.getHotelName()) ;
            }
        });
        mHotelsListLiveData.setValue(list);

    }

    private void sortHotelByPrice() {
        List<Hotel> list = mHotelsListLiveData.getValue();
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel, Hotel t1) {
                return hotel.getPrice().compareTo(t1.getPrice());
            }
        });

        mHotelsListLiveData.setValue(list);

    }

    private void sortHotelsByRate() {
        List<Hotel> list = mHotelsListLiveData.getValue();
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel, Hotel t1) {
                return hotel.getGuestRating().compareTo(t1.getGuestRating()) ;
            }
        });
        mHotelsListLiveData.setValue(list);

    }

}
