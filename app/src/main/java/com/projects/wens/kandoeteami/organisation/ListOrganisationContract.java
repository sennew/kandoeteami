package com.projects.wens.kandoeteami.organisation;

import android.support.annotation.NonNull;

import com.projects.wens.kandoeteami.organisation.data.Organisation;

import java.util.List;

public interface ListOrganisationContract {
    interface view{
        /**
         *
         * @param active
         */
        void setProgressIndicator(boolean active);

        /**
         *
         * @param organisations
         */
        void showOrganisations(List<Organisation> organisations);

        /**
         *
         * @param organisationId
         */
        void showOrganisationDetailUi(Integer organisationId);

        /**
         *
         * @param organisationId
         */
        void showOrganisationThemesUi(Integer organisationId);

        /**
         *
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener{
        /**
         *
         * @param forceUpdate
         * @param token
         */
        void loadOrganisations(boolean forceUpdate, String token);

        /**
         *
         * @param requestOrga
         */
        void openOrganisationThema(@NonNull Organisation requestOrga);

        /**
         *
         * @param requestOrga
         */
        void openOrganisationDetail(@NonNull Organisation requestOrga);
    }
}
