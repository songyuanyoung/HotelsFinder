package com.basicmoon.expediaassessment.details;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.databinding.FragmentHotelDetailsBinding;
import com.basicmoon.expediaassessment.utils.ActivityUtils;


public class HotelDetailsFragment extends Fragment {

    private HotelDetailsViewModel mHotelDetailsViewModel;

    private FragmentHotelDetailsBinding mFragmentHotelDetailsBinding;

    public HotelDetailsFragment() {
        // Required empty public constructor
    }

    public static HotelDetailsFragment newInstance() {
        HotelDetailsFragment fragment = new HotelDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotel_details, container, false);
        mFragmentHotelDetailsBinding = FragmentHotelDetailsBinding.bind(view);
        mHotelDetailsViewModel = HotelDetailsActivity.obtainViewModel(getActivity());

        mFragmentHotelDetailsBinding.setViewModel(mHotelDetailsViewModel);
        mFragmentHotelDetailsBinding.setLifecycleOwner(getActivity());

        mHotelDetailsViewModel.getHotelMutableLiveData().observe(getActivity(), new Observer<Hotel>() {
            @Override
            public void onChanged(Hotel hotel) {
                double rating = hotel.getGuestRating();

                mHotelDetailsViewModel.getRatingMutableLiveData().setValue(getRatingStringFromRating(rating));
            }
        });


        return mFragmentHotelDetailsBinding.getRoot();
    }

    private String getRatingStringFromRating(double rating) {
        return ActivityUtils.getRatingStringFromRating(getContext(), rating);
    }



}
