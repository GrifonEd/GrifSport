package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface OrganizerForEventServicePost {
    @POST(BuildConfig.MYURL+"/organizer-event/create/{event_id}/{user_id}")
    Call<OrganizerForEventResponse> OrganizerForEvent (@Path("event_id")Integer value, @Path("user_id")Integer value1);
}
