package com.basicmoon.expediaassessment.hotels.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basicmoon.expediaassessment.R;

import java.util.List;

import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.databinding.FragmentHotelsListBinding;
import com.basicmoon.expediaassessment.hotels.HotelsViewModel;
import com.basicmoon.expediaassessment.hotels.MapsActivity;

public class HotelsListFragment extends Fragment {

    private List<Hotel> mHotelList;

    private HotelsListRecyclerviewAdapter mHotelsListRecyclerviewAdapter;

    private RecyclerView mRecyclerView;

    private HotelsViewModel mHotelsViewModel;

    private FragmentHotelsListBinding mFragmentHotelsListBinding;

    public HotelsListFragment(List<Hotel> hotelList) {
        // Required empty public constructor
        mHotelList = hotelList;

        mHotelsListRecyclerviewAdapter = new HotelsListRecyclerviewAdapter(mHotelList);
    }


    public static HotelsListFragment newInstance(List<Hotel> hotelList) {
        HotelsListFragment fragment = new HotelsListFragment(hotelList);
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

        View view = inflater.inflate(R.layout.fragment_hotels_list, container, false);
        mFragmentHotelsListBinding = FragmentHotelsListBinding.bind(view);

        mRecyclerView = view.findViewById(R.id.hotels_list_recyclerview);
        mRecyclerView.setAdapter(mHotelsListRecyclerviewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        mHotelsViewModel = MapsActivity.obtainViewModel(getActivity());

        mFragmentHotelsListBinding.setViewmodel(mHotelsViewModel);
        mFragmentHotelsListBinding.setLifecycleOwner(getActivity());

        return mFragmentHotelsListBinding.getRoot();
    }


}
