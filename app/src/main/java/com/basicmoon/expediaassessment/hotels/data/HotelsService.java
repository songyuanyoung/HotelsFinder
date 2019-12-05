package com.basicmoon.expediaassessment.hotels.data;

import com.basicmoon.expediaassessment.data.model.HotelsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HotelsService {

    @GET("hotel_search_results.json")
    Observable<HotelsResponse> getNearByBikes();

}
