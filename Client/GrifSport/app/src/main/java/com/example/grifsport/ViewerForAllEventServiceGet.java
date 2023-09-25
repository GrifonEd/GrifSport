package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViewerForAllEventServiceGet {
    @GET(BuildConfig.MYURL+"/viewer-event/getAll/{profile_id}")
    Call<List<ViewerForEventResponse>> ViewerForEvent (@Path("profile_id")Integer value);
}
