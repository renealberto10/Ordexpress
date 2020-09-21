package com.pumpkinapplabs.ordexpress.data.remote;

import android.app.Service;
import android.telephony.ServiceState;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pumpkinapplabs.ordexpress.data.deserializer.DeserializerLogin;
import com.pumpkinapplabs.ordexpress.data.model.LoginPost;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    public static final String BASE_URL = "https://pumpkinorder.herokuapp.com/api/";
    private static ServicesAPI servicesAPI = null;

    public static ServicesAPI getClient() {
        if (servicesAPI==null) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(LoginPost.class, new DeserializerLogin());
         Retrofit  retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
            servicesAPI = retrofit.create(ServicesAPI.class);
        }
        return servicesAPI;
    }
    }
