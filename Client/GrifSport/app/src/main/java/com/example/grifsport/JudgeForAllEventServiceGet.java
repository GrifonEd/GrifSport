package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JudgeForAllEventServiceGet {
    @GET(BuildConfig.MYURL+"/judge-event/getAll/{profile_id}")
    Call<List<JudgeForEventResponse>> JudgeForEvent (@Path("profile_id")Integer value);
}
