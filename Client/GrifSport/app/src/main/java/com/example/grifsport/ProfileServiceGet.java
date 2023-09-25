package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileServiceGet {
    @GET(BuildConfig.MYURL+"/profile/get/{user_id}")
    Call<ProfileResponse> getProfile(@Path("user_id")Integer value);
}
