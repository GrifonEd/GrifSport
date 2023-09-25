package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DisciplineForEventServiceGet {
    @GET(BuildConfig.MYURL+"/discipline-event/{event_id}")
    Call<List<DisciplineForEventResponse>> getDisciplineForEvent(@Path("event_id")Integer value);
}
