package com.projects.wens.kandoeteami.organisation;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.organisation.data.Organisation;

/**
 * Created by michaelkees on 29/02/16.
 */
public interface OrganisationContract {

    interface View{
        void showSuccesMessage(String message);

        void showOrganisation(Organisation organisation, GroupItem item);



        String getOrganisationName();

        String getOrganisationDescription();

        void showErrorMessage(String message);
    }

    interface UserActionListener {
        void loadOrganisation(String token, int id);

        void loadUsers(String token, int organisationId);
    }
}
