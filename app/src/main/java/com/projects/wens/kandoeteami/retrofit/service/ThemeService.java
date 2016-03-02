package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Created by michaelkees on 01/03/16.
 */
public interface ThemeService {

    //TODO: services ophalen themes, (TOKEN, ID ORGA, /currentUser?)
    @GET("/organisations/{orgaId}/themes/currentUser")
    void getThemes(@Header("Authorization") String token, @Path("orgaId") int orgaId, Callback<List<Theme>> callback);


}