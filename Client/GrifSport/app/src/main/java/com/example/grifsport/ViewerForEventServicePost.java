package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ViewerForEventServicePost {
    @POST(BuildConfig.MYURL+"/viewer-event/create/{event_id}/{profile_id}")
    Call<ViewerForEventResponse> viewerForEventServicePost (@Path("event_id")Integer value,@Path("profile_id")Integer value1);
}
