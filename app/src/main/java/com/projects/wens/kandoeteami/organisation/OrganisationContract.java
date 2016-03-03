package com.projects.wens.kandoeteami.organisation;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.user.data.User;

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

        void showErrorMessage(String message);

        void showMembers(List<User> users);

        void showOrganisers(List<User> users);
    }

    interface UserActionListener {
        void loadOrganisation(String token, int id);

        void loadMembers(String token, int organisationId);

        void loadOrganisers(String token, int organisationId);
    }
}
