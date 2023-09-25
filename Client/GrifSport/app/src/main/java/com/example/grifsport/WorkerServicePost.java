package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WorkerServicePost {
    @POST(BuildConfig.MYURL+"/worker/create")
    Call<WorkerResponse> workerPost (@Body WorkerResponse workerResponse);
}
