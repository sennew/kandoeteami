package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.login.data.AccesToken;

import retrofit.Callback;
import retrofit.http.POST;

/**
 * Created by senne on 24/02/2016.
 */
public interface LoginService {
    @POST("/login")
    void login(String username, String password, Callback<AccesToken> callback);
}
