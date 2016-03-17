package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Created by senne on 14/03/2016.
 */
public interface SubThemaService {
    @GET("/subThemes")
    void getSubThemes(@Header("Authorization") String token, Callback<List<SubTheme>> callback);

    @GET("/subThemes/{themeId}")
    void getSubTheme(@Header("Authorization") String token, @Path("themeId") int id,Callback<SubTheme> callback);

    @GET("/themes/{themeId}/subThemes")
    void getSubthemesByThemeId(@Header("Authorization") String token, @Path("themeId") int id, Callback<List<SubTheme>> callback);

    @GET("/sessions/subtheme/{subThemeId}")
    void getSessionBySubThemeId(@Header("Authorization") String token, @Path("subThemeId") int id, Callback<List<SessionDTO>> callback);
}
