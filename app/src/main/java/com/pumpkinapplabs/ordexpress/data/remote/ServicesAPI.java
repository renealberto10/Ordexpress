package com.pumpkinapplabs.ordexpress.data.remote;

import com.pumpkinapplabs.ordexpress.data.model.LoginPost;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServicesAPI {
    @POST("auth/login")
    @FormUrlEncoded
    Call<LoginPost> savePost(@Field("email")String email,
                             @Field ("password") String password);
}
