package com.projects.wens.kandoeteami.organisation;

import com.projects.wens.kandoeteami.organisation.data.Organisation;

import java.util.List;

/**
 * Created by michaelkees on 29/02/16.
 */
public interface OrganisationContract {

    interface View{
        void showSuccesMessage(String message);
        void showOrganisation(Organisation organisation);
        String getOrganisationName();
        String getOrganisationDescription();
        List<String> getOrganisationMembers();
        List<String> getOrganisationOrganisers();

    }

    interface UserActionListener {
        void loadOrganisation(String token, int id);
    }
}
