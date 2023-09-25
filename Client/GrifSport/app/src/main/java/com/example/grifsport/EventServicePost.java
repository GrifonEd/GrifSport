package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EventServicePost {
    @POST(BuildConfig.MYURL+"/event/create")
    Call<EventResponse> eventPost (@Body EventResponse event);
}
