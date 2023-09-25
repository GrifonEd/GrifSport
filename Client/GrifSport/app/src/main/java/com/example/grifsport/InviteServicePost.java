package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InviteServicePost {
    @POST(BuildConfig.MYURL+"/invite/create")
    Call<InviteResponsePost>postInvite(@Body InviteResponsePost inviteResponsePost);
}
