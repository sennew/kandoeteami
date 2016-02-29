package com.projects.wens.kandoeteami.login;

import com.projects.wens.kandoeteami.login.data.AccesToken;

/**
 * Created by senne on 23/02/2016.
 */
public interface LoginContract {
    interface view{
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
        void showOrganisationsActivity();
        void saveToken(String token);
        String getUsername();
        String getPassword();

        void showProgressLogin();

        void stopProgress();

        void showUsernameError(String message);

        void showPasswordError(String message);
    }

    interface UserActionListener{
        void login();
    }

}
