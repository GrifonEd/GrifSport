package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JudgeServicePost {
    @POST(BuildConfig.MYURL+"/judge/create")
    Call<JudgeResponse> JudgePost (@Body JudgeResponse organizerResponse);
}
