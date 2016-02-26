package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.login.data.LoginDTO;

import retrofit.Callback;
import retrofit.http.POST;

/**
 * Created by senne on 24/02/2016.
 */
public interface LoginService {
    @POST("/login")
    void login(LoginDTO loginDTO, Callback<String> callback);
}
