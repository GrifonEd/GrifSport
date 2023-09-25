package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventServiceGet {
    @GET(BuildConfig.MYURL+"/event/all")
    Call<List<EventResponse>> getEvent();
}
