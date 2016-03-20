package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.login.data.LoginDTO;
import com.projects.wens.kandoeteami.user.data.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;


public interface LoginService {

    /**
     * rest call login
     * @param loginDTO
     * @param callback
     */
    @POST("/login")
    void login(@Body LoginDTO loginDTO, Callback<String> callback);

    /**
     * rest call login with facebook
     * @param user
     * @param callback
     */
    @POST("/login/facebook")
    void loginFacebook(@Body User user, Callback<String> callback);

}
