package com.example.grifsport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApplicationServicePost {
    @POST(BuildConfig.MYURL+"/application/create")
    Call<ApplicationResponsePost>postApplication(@Body ApplicationResponsePost applicationResponse);
}
