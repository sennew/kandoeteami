package com.projects.wens.kandoeteami.organisation;

/**
 * Created by michaelkees on 29/02/16.
 */
public interface OrganisationContract {

    interface View{
        int getOrganisationID();
        void showOrganisation();
    }

    interface UserActionListener {
        void loadOrganisation(int ID);
    }
}
