package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ParticipantForAllEventServiceGet {
    @GET(BuildConfig.MYURL+"/participant-event/getAll/{profile_id}")
    Call<List<ParticipantForEventResponse>> ParticipantForEvent (@Path("profile_id")Integer value);
}
