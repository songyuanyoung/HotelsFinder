package com.basicmoon.expediaassessment.hotels.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.databinding.FragmentHotelsListBinding;
import com.basicmoon.expediaassessment.hotels.HotelsMainActivity;
import com.basicmoon.expediaassessment.hotels.HotelsViewModel;
import com.basicmoon.expediaassessment.hotels.OnOpenHotelDetailsListener;

import java.util.List;

public class HotelsListFragment extends Fragment implements OnOpenHotelDetailsListener {

    private List<Hotel> mHotelList;

    private HotelsListRecyclerviewAdapter mHotelsListRecyclerviewAdapter;

    private RecyclerView mRecyclerView;

    private HotelsViewModel mHotelsViewModel;

    private FragmentHotelsListBinding mFragmentHotelsListBinding;

    public HotelsListFragment(List<Hotel> hotelList) {
        // Required empty public constructor
        mHotelList = hotelList;

        mHotelsListRecyclerviewAdapter = new HotelsListRecyclerviewAdapter(mHotelList);
        mHotelsListRecyclerviewAdapter.setOnOpenHotelDetailsListener(this);
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

        View view = inflater.inflate(R.layout.fragment_hotels_list, container, false);
        mFragmentHotelsListBinding = FragmentHotelsListBinding.bind(view);

        mRecyclerView = view.findViewById(R.id.hotels_list_recyclerview);
        mRecyclerView.setAdapter(mHotelsListRecyclerviewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        mHotelsViewModel = HotelsMainActivity.obtainViewModel(getActivity());

        mFragmentHotelsListBinding.setViewmodel(mHotelsViewModel);
        mFragmentHotelsListBinding.setLifecycleOwner(getActivity());

        mHotelsViewModel.getHotelsListLiveData().observe(getActivity(), new Observer<List<Hotel>>() {
            @Override
            public void onChanged(List<Hotel> hotels) {
                mRecyclerView.setAdapter(mHotelsListRecyclerviewAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
            }
        });

        return mFragmentHotelsListBinding.getRoot();
    }


    @Override
    public void onOpenHotelDetails(String hotelName) {
        mHotelsViewModel.openHotelDetails(hotelName);
    }
}
