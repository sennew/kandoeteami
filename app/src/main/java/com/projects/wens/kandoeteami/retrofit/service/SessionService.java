package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Created by senne on 15/03/2016.
 */
public interface SessionService {
    @GET("/sessions/theme/{themeId}")
    void getSessionsByThemeId(@Header("Authorization") String token, @Path("themeId") int id, Callback<List<SessionDTO>> callback);

    @GET("/sessions/{sessionId}")
    void getSessionById(@Header("Authorization") String token, @Path("sessionId") int sessionId, Callback<SessionDTO> callback);
}
