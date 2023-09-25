package com.example.grifsport;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Authorization {
    @GET(BuildConfig.MYURL+"/auth/users/me/")
    Call<AuthorizationResponse> getloginUser(@Header("Authorization") String header);
}
