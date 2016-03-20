package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.register.data.RegisterDTO;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;


public interface RegisterService {

    /**
     * rest call post users to create user object
     * @param registerDTO
     * @param callback
     */
    @POST("/users")
    void register(@Body RegisterDTO registerDTO, Callback<String> callback);


}
