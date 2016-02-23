package com.projects.wens.kandoeteami.retrofit.service;

import com.projects.wens.kandoeteami.organisation.data.Organisation;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by senne on 23/02/2016.
 */
public interface OrganisationService {
    @GET("/organisations")
    void getOrganisations(Callback<List<Organisation>> callback);
    @GET("/organisations/{id}")
    void getOrganisation(@Path("id") int organisationId, Callback<Organisation> callback);
}
