package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.login.data.LoginDTO;
import com.projects.wens.kandoeteami.register.data.RegisterDTO;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by michaelkees on 28/02/16.
 */
public interface RegisterService {

    @POST("/register")
    void register(@Body RegisterDTO registerDTO, Callback<String> callback);

}