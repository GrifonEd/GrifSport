package com.example.grifsport;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServiceLogin {
    @POST(BuildConfig.MYURL+"/auth/jwt/create/")
    Call<UserLoginResponse> loginUser(@Body UserLoginResponse user);
}
