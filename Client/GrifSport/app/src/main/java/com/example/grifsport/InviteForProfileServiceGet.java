package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InviteForProfileServiceGet {
    @GET(BuildConfig.MYURL+"/invite/profile/{id_profile}")
    Call<List<InviteResponse>> getInvite(@Path("id_profile")Integer value);
}
