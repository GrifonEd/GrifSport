package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface InviteServiceDelete {
    @DELETE(BuildConfig.MYURL+"/invite/{id_invite}")
    Call<InviteResponse>InviteServiceDelete(@Path("id_invite")Integer value);
}
