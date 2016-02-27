package com.projects.wens.kandoeteami.organisation;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;

import java.io.Console;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;


/**
 * Created by senne on 22/02/2016.
 */
public class ListOrganisationPresenter implements ListOrganisationContract.UserActionListener {
    private final ListOrganisationContract.view view;
    private final OrganisationService service;

    public ListOrganisationPresenter(ListOrganisationContract.view view, OrganisationService serviceOrga){
        this.view = checkNotNull(view, "organisationsView cannot be null!");
        this.service = serviceOrga;
    }


    @Override
    public void loadOrganisations(boolean forceUpdate, String token) {
        if (view != null){
            view.setProgressIndicator(true);
        }

        service.getOrganisations("Bearer "+token, new Callback<List<Organisation>>() {
            @Override
            public void success(List<Organisation> organisations, Response response) {
                if (view != null){
                    view.showOrganisations(organisations);
                    view.setProgressIndicator(false);
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
        view.showOrganisationDetailUi(requestOrga.getOrganistionId());
    }
}
