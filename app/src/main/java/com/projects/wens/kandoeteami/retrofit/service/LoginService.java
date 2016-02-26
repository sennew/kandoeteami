package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.login.data.LoginDTO;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;


public interface LoginService {

    @POST("/login")
    void login(@Body LoginDTO loginDTO, Callback<String> callback);
}
