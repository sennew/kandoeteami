package com.projects.wens.kandoeteami.login;

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

        void saveUserDetails(String firstname, String lastname, String profilePicture, String email);


        void logoutFacebook();
    }

    interface UserActionListener{
        void login();

        void loginWithFacebook(String username, String firstname, String lastname, String email);
    }

}
