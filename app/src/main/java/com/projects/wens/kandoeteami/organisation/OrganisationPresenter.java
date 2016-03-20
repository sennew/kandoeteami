package com.projects.wens.kandoeteami.organisation;

import com.projects.wens.kandoeteami.organisation.data.ChildItem;
import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.projects.wens.kandoeteami.user.data.User;

import java.util.List;

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
    public void loadOrganisation(final String token,final int id) {
        final Organisation orga = new Organisation();
        //loading progress
        service.getOrganisation("Bearer " + token, id, new Callback<Organisation>() {
            @Override
            public void success(Organisation organisation, Response response) {
                view.showSuccesMessage("Successfully loaded organisations");
                orga.setOrganisationId(organisation.getOrganisationId());
                orga.setLogoURL(organisation.getLogoURL());
                orga.setAddress(organisation.getAddress());
                orga.setLinks(organisation.getLinks());
                orga.setOrganisationName(organisation.getOrganisationName());
                getOrganisers(token,id,orga);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getCause().toString());
            }
        });
    }


    private void getOrganisers(final String token, final int id, final Organisation orga){
        final GroupItem item = new GroupItem("GROUP MEMBERS");
        service.getOrganisationOrganisers("Bearer " + token, id, new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                if (users != null){
                    for (User u : users) {
                        ChildItem child = new ChildItem(u.getPerson().getFirstname(), u.getPerson().getLastname(), "ORGANISER", u.getProfilePicture());
                        item.addChildren(child);
                    }
                }

                getMembers(token, id,orga,item);
            }
            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getMessage());
            }
        });
    }

    private void getMembers(String token, int id, Organisation orga, GroupItem group){
        final Organisation organi = orga;
        final GroupItem item = group;

        service.getOrganisationMembers("Bearer " + token, id, new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                if (users != null){
                    for (User u : users) {
                        ChildItem child = new ChildItem(u.getPerson().getFirstname(), u.getPerson().getLastname(), "MEMBER", u.getProfilePicture());
                        item.addChildren(child);
                    }
                }
                view.showOrganisation(organi, item);
            }
            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getMessage());
            }
        });

    }

}
