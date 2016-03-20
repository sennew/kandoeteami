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

public interface OrganisationService {

    /**
     *
     * @param token
     * @param callback
     */
    @GET("/organisations/currentUser")
    void getOrganisations(@Header("Authorization") String token, Callback<List<Organisation>> callback);

    /**
     *
     * @param token
     * @param organisationId
     * @param callback
     */
    @GET("/organisations/{id}")
    void getOrganisation(@Header("Authorization") String token, @Path("id") int organisationId, Callback<Organisation> callback);

    /**
     *
     * @param token
     * @param organisationId
     * @param userCallback
     */
    @GET("/organisations/{orgId}/members")
    void getOrganisationMembers(@Header("Authorization") String token, @Path("orgId") int organisationId, Callback<List<User>> userCallback);

    /**
     *
     * @param token
     * @param organisationId
     * @param userCallback
     */
    @GET("/organisations/{orgId}/organisers")
    void getOrganisationOrganisers(@Header("Authorization") String token, @Path("orgId") int organisationId, Callback<List<User>> userCallback);

    /**
     *
     * @param token
     * @param organisationId
     * @param user
     * @param userCallback
     */
    @POST("/organisations/{orgId}/addMember")
    void addOrganisationMember(@Header("Authorization") String token, @Path("orgId") int organisationId, @Body User user, Callback<User> userCallback);
}
