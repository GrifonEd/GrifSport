package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApplicationsForEventServiceGet {
    @GET(BuildConfig.MYURL+"/application/event/{id_event}")
    Call<List<ApplicationResponse>> getApplications(@Path("id_event")Integer value);
}
