package com.basicmoon.expediaassessment.hotels.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basicmoon.expediaassessment.R;

import java.util.List;

import com.basicmoon.expediaassessment.data.model.Hotel;

public class HotelsListRecyclerviewAdapter extends RecyclerView.Adapter<HotelsListRecyclerviewAdapter.ViewHolder> {


    private List<Hotel> mHotelList;


    public HotelsListRecyclerviewAdapter(List<Hotel> hotelList) {
        mHotelList = hotelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_hotel, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel hotel = mHotelList.get(position);

        holder.mHotelnameTextView.setText(hotel.getHotelName());
        holder.mHotelDescriptiontextView.setText(hotel.getDescription());

    }

    @Override
    public int getItemCount() {
        return mHotelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mHotelnameTextView;

        private TextView mHotelDescriptiontextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mHotelnameTextView = (TextView) itemView.findViewById(R.id.hotel_name_textview);
            mHotelDescriptiontextView = (TextView) itemView.findViewById(R.id.hotel_description_textview);
        }

    }
}
