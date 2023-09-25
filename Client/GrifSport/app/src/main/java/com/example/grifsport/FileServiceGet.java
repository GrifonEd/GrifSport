package com.example.grifsport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface FileServiceGet {
    @Streaming
    @GET(BuildConfig.MYURL+"/media/pdfs/{pdf_name}")
    Call<FileResponse> fileGet (@Path("pdf_name")String pdf_name);
}
