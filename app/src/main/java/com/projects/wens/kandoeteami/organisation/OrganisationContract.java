package com.projects.wens.kandoeteami.organisation;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.organisation.data.Organisation;


public interface OrganisationContract {

    interface View{

        /**
         *
         * @param message
         */
        void showSuccesMessage(String message);

        /**
         *
         * @param organisation
         * @param item
         */
        void showOrganisation(Organisation organisation, GroupItem item);

        /**
         *
         * @return
         */
        String getOrganisationName();

        /**
         *
         * @return
         */
        String getOrganisationDescription();

        /**
         *
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener {
        /**
         *
         * @param token
         * @param id
         */
        void loadOrganisation(String token, int id);
    }
}
