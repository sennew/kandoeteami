package com.projects.wens.kandoeteami.organisation;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.organisation.data.Organisation;


public interface OrganisationContract {

    interface View{

        /**
         * show succes message on success rest calls
         * @param message
         */
        void showSuccesMessage(String message);

        /**
         * show organisation in view component with param organisation and groupItem
         * @param organisation
         * @param item
         */
        void showOrganisation(Organisation organisation, GroupItem item);

        /**
         * show error message on failure rest calls
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener {
        /**
         * getting organisation from rest call with param token and userId
         * @param token
         * @param id
         */
        void loadOrganisation(String token, int id);
    }
}
