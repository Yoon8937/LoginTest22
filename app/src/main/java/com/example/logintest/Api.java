package com.example.logintest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import retrofit2.Call;

import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    String DJANGO_SITE="http://13.125.171.174/";

    @Multipart
    //@Headers({"Content-Type: multipart/form-data"})
    @POST("/image_app/")
    Call<RequestBody> uploadImage(@Part MultipartBody.Part file);
    //Call<ResponseBody> uploadImage(@Field("model_pic") JsonObject jsonObject);
    //Call<ResponseBody> uploadImage(@Body JsonObject jsonObject);

}
