package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ParticipantServicePost {
    @POST(BuildConfig.MYURL+"/participant/create")
    Call<ParticipantResponse> ParticipantPost (@Body ParticipantResponse organizerResponse);
}
