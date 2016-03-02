package com.projects.wens.kandoeteami.organisation;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class OrganisationPresenter implements OrganisationContract.UserActionListener {
    private final OrganisationContract.View view;
    private final OrganisationService service;

    public OrganisationPresenter(OrganisationContract.View view, OrganisationService service) {
        this.view = view;
        this.service = service;
    }


    @Override
    public void loadOrganisation(String token, int id) {

        //loading progress
        service.getOrganisation("Bearer " + token, id, new Callback<Organisation>() {
            @Override
            public void success(Organisation organisation, Response response) {
                view.showOrganisation(organisation);
                view.showSuccesMessage("Successfully loaded organisations");
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getCause().toString());
            }
        });
    }
}
