package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrganizerServiceGet {
    @GET(BuildConfig.MYURL+"/organizer/profile/{profile_id}")
    Call<OrganizerResponse> OrganizerGet (@Path("profile_id")Integer value);
}
