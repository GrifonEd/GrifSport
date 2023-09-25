package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface EventServicePut {
    @PUT(BuildConfig.MYURL+"/event/create")
    Call<EventResponse> eventPut (@Body EventResponse event);
}
