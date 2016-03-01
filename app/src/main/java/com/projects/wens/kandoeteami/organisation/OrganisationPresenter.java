package com.projects.wens.kandoeteami.organisation;

import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;

/**
 * Created by michaelkees on 29/02/16.
 */
public class OrganisationPresenter implements OrganisationContract.UserActionListener {
    private final OrganisationContract.View view;
    private final OrganisationService service;

    public OrganisationPresenter(OrganisationContract.View view, OrganisationService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void loadOrganisation(String token, int id) {

    }
}
