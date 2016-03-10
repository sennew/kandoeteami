package com.projects.wens.kandoeteami.organisation;

import android.support.annotation.NonNull;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;


public class ListOrganisationPresenter implements ListOrganisationContract.UserActionListener {
    private final ListOrganisationContract.view view;
    private final OrganisationService service;
    private List<Organisation> list = new ArrayList<>();


    public ListOrganisationPresenter(ListOrganisationContract.view view, OrganisationService serviceOrga){
        this.view = checkNotNull(view, "organisationsView cannot be null!");
        this.service = serviceOrga;
    }


    @Override
    public void loadOrganisations(boolean forceUpdate, final String token) {
        if (view != null){
            view.setProgressIndicator(true);
        }

        service.getOrganisations("Bearer " + token, new Callback<List<Organisation>>() {
            @Override
            public void success(List<Organisation> organisations, Response response) {
                if (view != null) {
                    view.setProgressIndicator(false);
                    view.showOrganisations(organisations);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getResponse().getStatus());
            }
        });

    }



    @Override
    public void openOrganisationThema(@NonNull Organisation requestOrga) {
        view.showOrganisationThemesUi(requestOrga.getOrganisationId());
    }

    @Override
    public void openOrganisationDetail(@NonNull Organisation requestOrga) {
        view.showOrganisationDetailUi(requestOrga.getOrganisationId());
    }
}
