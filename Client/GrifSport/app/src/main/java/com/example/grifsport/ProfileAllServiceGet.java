package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileAllServiceGet {
    @GET(BuildConfig.MYURL+"/profile/all")
    Call<List<ProfileResponse>> getAllProfile();
}
