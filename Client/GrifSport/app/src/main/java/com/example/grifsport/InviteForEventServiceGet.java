package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InviteForEventServiceGet {
    @GET(BuildConfig.MYURL+"/invite/event/{id_event}")
    Call<List<InviteResponse>> getInvite(@Path("id_event")Integer value);
}
