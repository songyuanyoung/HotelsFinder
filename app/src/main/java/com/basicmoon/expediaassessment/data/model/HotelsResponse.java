package com.basicmoon.expediaassessment.data.model;

import com.basicmoon.expediaassessment.data.model.Hotel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HotelsResponse {
    @SerializedName("hotels")
    @Expose
    private List<Hotel> hotels = null;
}
