package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WorkerForEventServicePost {
    @POST(BuildConfig.MYURL+"/worker-event/create/{event_id}/{profile_id}")
    Call<WorkerForEventResponse> WorkerForEventServicePost (@Path("event_id")Integer value,@Path("profile_id")Integer value1);
}
