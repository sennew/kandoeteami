package com.projects.wens.kandoeteami.retrofit.service;

import android.telecom.Call;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.user.data.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by senne on 23/02/2016.
 */
public interface OrganisationService {

    @GET("/organisations/currentUser")
    void getOrganisations(@Header("Authorization") String token, Callback<List<Organisation>> callback);

    @GET("/organisations/{id}")
    void getOrganisation(@Header("Authorization") String token, @Path("id") int organisationId, Callback<Organisation> callback);

    @GET("/organisations/{orgId}/members")
    void getOrganisationMembers(@Header("Authorization") String token, @Path("orgId") int organisationId, Callback<List<User>> userCallback);

    @GET("/organisations/{orgId}/organisers")
    void getOrganisationOrganisers(@Header("Authorization") String token, @Path("orgId") int organisationId, Callback<List<User>> userCallback);

    @POST("/organisations/{orgId}/addMember")
    void addOrganisationMember(@Header("Authorization") String token, @Path("orgId") int organisationId, @Body User user, Callback<User> userCallback);
}
