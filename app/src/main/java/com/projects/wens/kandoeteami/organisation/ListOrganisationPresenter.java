package com.projects.wens.kandoeteami.organisation;

import android.support.annotation.NonNull;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.organisation.data.OrganisationList;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.projects.wens.kandoeteami.user.data.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;


public class ListOrganisationPresenter implements ListOrganisationContract.UserActionListener {
    private final ListOrganisationContract.view view;
    private final OrganisationService service;
    private List<OrganisationList> list = new ArrayList<>();


    public ListOrganisationPresenter(ListOrganisationContract.view view, OrganisationService serviceOrga){
        this.view = checkNotNull(view, "organisationsView cannot be null!");
        this.service = serviceOrga;
    }


    @Override
    public void loadOrganisations(boolean forceUpdate, final String token) {
        if (view != null){
            view.setProgressIndicator(true);
        }

        service.getOrganisations("Bearer "+token, new Callback<List<Organisation>>() {
            @Override
            public void success(List<Organisation> organisations, Response response) {
                list.clear();
                for (Organisation o: organisations){
                    OrganisationList orga = new OrganisationList(o.getOrganisationName(), o.getAddress());
                    orga.setLogoURL(o.getLogoURL());
                    orga.setOrganisationId(o.getOrganisationId());
                    list.add(orga);
                    loadCountUsersOrganizers(token, o.getOrganisationId());
                }

                if (view != null){
                    view.setProgressIndicator(false);
                    view.showOrganisations(list);
                }

            }
            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getResponse().getStatus());
            }
        });

    }


    private void loadCountUsersOrganizers(final String token,final int orgaId){
        service.getOrganisationOrganisers("Bearer " + token, orgaId, new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                createUsersList(orgaId, users.size());
                loadCountUsersMembers(token,orgaId);
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO:FAILURE UITWERKEN
            }
        });

    }

    private void loadCountUsersMembers(String token, final int orgaId){
        service.getOrganisationMembers("Bearer " +token, orgaId, new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                createUsersList(orgaId, users.size());
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO:FAILURE UITWERKEN
            }
        });
    }

    private void createUsersList(int orgaId, int aantalUsers){
        for (OrganisationList l: list){
            if (l.getOrganisationId() == orgaId){
                int aantal = l.getAantalUsers();
                aantal += aantalUsers;
                l.setAantalUsers(aantal);
            }
        }
    }

    @Override
    public void openOrganisationThema(@NonNull OrganisationList requestOrga) {
        view.showOrganisationDetailUi(requestOrga.getOrganisationId());
    }
}
