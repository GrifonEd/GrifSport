package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApplicationServiceDelete {
    @DELETE(BuildConfig.MYURL+"/application/{id_application}")
    Call<ApplicationResponse>ApplicationServiceDelete(@Path("id_application")Integer value);
}
