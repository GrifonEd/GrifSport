package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OneEventServiceGet {
    @GET(BuildConfig.MYURL+"/event/{event_id}")
    Call<EventResponse> getEvent(@Path("event_id")Integer value);
}
