package com.basicmoon.expediaassessment.hotels.list;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.basicmoon.expediaassessment.R;
import com.basicmoon.expediaassessment.data.model.Hotel;
import com.basicmoon.expediaassessment.hotels.OnOpenHotelDetailsListener;
import com.basicmoon.expediaassessment.utils.ActivityUtils;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import timber.log.Timber;

public class HotelsListRecyclerviewAdapter extends RecyclerView.Adapter<HotelsListRecyclerviewAdapter.ViewHolder> {


    private List<Hotel> mHotelList;

    private Context mContext;

    private Picasso.Builder mBuilder;


    private OnOpenHotelDetailsListener mOnOpenHotelDetailsListener;

    public HotelsListRecyclerviewAdapter(List<Hotel> hotelList) {
        mHotelList = hotelList;
    }

    public void setOnOpenHotelDetailsListener(OnOpenHotelDetailsListener onOpenHotelDetailsListener) {
        mOnOpenHotelDetailsListener = onOpenHotelDetailsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_hotel, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel hotel = mHotelList.get(position);

        holder.mHotelItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnOpenHotelDetailsListener != null) {
                    mOnOpenHotelDetailsListener.onOpenHotelDetails(hotel.getHotelName());
                }
            }
        });

        holder.mHotelItemNameTextView.setText(hotel.getHotelName());
        mBuilder = new Picasso.Builder(mContext);
        mBuilder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {

                Timber.d("onImageLoadFailed");
                picasso.load(R.drawable.hotel_defalt).into(holder.mHotelItemImageView);
            }
        });


        mBuilder.build().load(hotel.getHotelImageURL()).into(holder.mHotelItemImageView);



        Double rating = hotel.getGuestRating();

        if (rating >= 4.0f) {
            holder.mHotelItemRatingTextView.setTextColor(Color.BLUE);
        } else if (rating >= 3.0f) {
            holder.mHotelItemRatingTextView.setTextColor(Color.GREEN);
        } else if (rating >= 2.0f) {
            holder.mHotelItemRatingTextView.setTextColor(Color.MAGENTA);
        } else {
            holder.mHotelItemRatingTextView.setTextColor(Color.RED);

        }
        holder.mHotelItemRatingTextView.setText(ActivityUtils.getRatingStringFromRating(mContext, hotel.getGuestRating()));

        holder.mHotelItemPriceTextView.setText(hotel.getPrice());

        if (TextUtils.isEmpty(hotel.getDiscountMessage())) holder.mHotelItemDiscountTextView.setVisibility(View.INVISIBLE);
        holder.mHotelItemDiscountTextView.setText(hotel.getDiscountMessage());

    }

    @Override
    public int getItemCount() {
        return mHotelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView mHotelItemCardView;
        private ImageView mHotelItemImageView;
        private TextView mHotelItemNameTextView;
        private TextView mHotelItemRatingTextView;
        private TextView mHotelItemPriceTextView;
        private TextView mHotelItemDiscountTextView;

        private TextView mHotelDescriptiontextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mHotelItemCardView = (CardView) itemView.findViewById(R.id.hotel_item_cardview);
            mHotelItemImageView = (ImageView) itemView.findViewById(R.id.hotel_item_imageView);
            mHotelItemNameTextView = (TextView) itemView.findViewById(R.id.hotel_item_name_textview);
            mHotelItemRatingTextView = (TextView) itemView.findViewById(R.id.hotel_item_rating_textView);
            mHotelItemPriceTextView = (TextView) itemView.findViewById(R.id.hotel_item_price_textview);
            mHotelItemDiscountTextView = (TextView) itemView.findViewById(R.id.hotel_item_discount_textview);
        }

    }
}
