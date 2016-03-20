package com.projects.wens.kandoeteami.organisation;

import android.support.annotation.NonNull;

import com.projects.wens.kandoeteami.organisation.data.Organisation;

import java.util.List;

public interface ListOrganisationContract {
    interface view{
        /**
         * set progress indicator true or false -> loading organisations
         * @param active
         */
        void setProgressIndicator(boolean active);

        /**
         * show organisations in view component with param list organisations
         * @param organisations
         */
        void showOrganisations(List<Organisation> organisations);

        /**
         * show organisation detail activity with param organisationId
         * @param organisationId
         */
        void showOrganisationDetailUi(Integer organisationId);

        /**
         * show organisation themes activity with param organisationId
         * @param organisationId
         */
        void showOrganisationThemesUi(Integer organisationId);

        /**
         * show error message --> on failure
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener{
        /**
         * loading organisations -> rest call organisation service with token <- Preferences
         * @param forceUpdate
         * @param token
         */
        void loadOrganisations(boolean forceUpdate, String token);

        /**
         * show organisation theme activity with request organisation
         * @param requestOrga
         */
        void openOrganisationTheme(@NonNull Organisation requestOrga);

        /**
         * show organisation detail activity with request organisation
         * @param requestOrga
         */
        void openOrganisationDetail(@NonNull Organisation requestOrga);
    }
}
