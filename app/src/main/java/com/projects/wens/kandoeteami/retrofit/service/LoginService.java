package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.login.data.AccesToken;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by senne on 24/02/2016.
 */
public interface LoginService {

    @POST("/login")
    void login(@Field("email") String email,@Field("password") String password, Callback<AccesToken> callback);
}
