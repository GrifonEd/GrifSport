package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JudgeForEventServicePost {
    @POST(BuildConfig.MYURL+"/judge-event/create/{event_id}/{profile_id}")
    Call<JudgeForEventResponse> judgeForEventServicePost (@Path("event_id")Integer value,@Path("profile_id")Integer value1);
}
