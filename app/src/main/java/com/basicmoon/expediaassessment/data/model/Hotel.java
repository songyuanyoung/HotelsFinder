package com.basicmoon.expediaassessment.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(tableName = "hotel")
public class Hotel implements Serializable {

    @SerializedName("starRating")
    @Expose
    private String starRating;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("loyaltyPointsEarned")
    @Expose
    private String loyaltyPointsEarned;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("hotelImageURL")
    @Expose
    private String hotelImageURL;

    @SerializedName("guestRating")
    @Expose
    private String guestRating;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("hotelName")
    @Expose
    @PrimaryKey
    @NonNull
    private String hotelName;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("discountMessage")
    @Expose
    private String discountMessage;
}
