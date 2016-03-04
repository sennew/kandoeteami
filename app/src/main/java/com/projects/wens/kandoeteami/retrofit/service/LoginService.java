package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.login.data.LoginDTO;
import com.projects.wens.kandoeteami.user.data.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;


public interface LoginService {

    @POST("/login")
    void login(@Body LoginDTO loginDTO, Callback<String> callback);

    @POST("/login/facebook")
    void loginFacebook(@Body User user, Callback<String> callback);

}
