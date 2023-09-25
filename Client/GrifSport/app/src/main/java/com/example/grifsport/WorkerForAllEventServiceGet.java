package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WorkerForAllEventServiceGet {
    @GET(BuildConfig.MYURL+"/worker-event/getAll/{profile_id}")
    Call<List<WorkerForEventResponse>> WorkerForEvent (@Path("profile_id")Integer value);
}
