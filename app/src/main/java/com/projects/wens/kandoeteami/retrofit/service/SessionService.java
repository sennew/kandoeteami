package com.projects.wens.kandoeteami.retrofit.service;

import android.telecom.Call;

import com.projects.wens.kandoeteami.session.data.CurrentMove;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by senne on 15/03/2016.
 */
public interface SessionService {

    /**
     *
     * @param token
     * @param callback
     */
    @GET("/sessions/currentUser")
    void getSessions(@Header("Authorization") String token, Callback<List<SessionDTO>> callback);

    /**
     *
     * @param token
     * @param sessionId
     * @param callback
     */
    @GET("/sessions/{sessionId}")
    void getSessionById(@Header("Authorization") String token, @Path("sessionId") int sessionId, Callback<SessionDTO> callback);

    /**
     *
     * @param token
     * @param id
     * @param callback
     */
    @GET("/sessions/theme/{themeId}")
    void getSessionsByThemeId(@Header("Authorization") String token, @Path("themeId") int id, Callback<List<SessionDTO>> callback);

    /**
     *
     * @param token
     * @param sessionId
     * @param callback
     */
    @GET("/sessions/{sessionId}/canPlay")
    void checkCanUserPlay(@Header("Authorization") String token, @Path("sessionId") int sessionId, Callback<Boolean> callback);

    /**
     *
     * @param token
     * @param move
     * @param callback
     */
    @POST("/sessions/makeMove")
    void makeMove(@Header("Authorization") String token, @Body CurrentMove move, Callback<SessionDTO> callback);
}
