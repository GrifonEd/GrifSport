package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrganizerServicePost {
    @POST(BuildConfig.MYURL+"/organizer/create")
    Call<OrganizerResponse> OrganizerPost (@Body OrganizerResponse organizerResponse);
}
