package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServicePost {
    @POST(BuildConfig.MYURL+"/auth/users/")
    Call<UserResponse> createUser(@Body UserResponse user);
}
