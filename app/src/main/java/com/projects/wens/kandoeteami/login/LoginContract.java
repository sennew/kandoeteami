package com.projects.wens.kandoeteami.login;

/**
 * Created by senne on 23/02/2016.
 */
public interface LoginContract {
    interface view{
        /**
         *
         * @param message
         */
        void showErrorMessage(String message);

        /**
         *
         * @param message
         */
        void showSuccessMessage(String message);

        /**
         *
         */
        void showOrganisationsActivity();

        /**
         *
         * @param token
         */
        void saveToken(String token);

        /**
         *
         * @return
         */
        String getUsername();

        /**
         *
         * @return
         */
        String getPassword();

        /**
         *
         */
        void showProgressLogin();


        /**
         *
         */
        void stopProgress();


        /**
         *
         * @param message
         */
        void showUsernameError(String message);

        /**
         *
         * @param message
         */
        void showPasswordError(String message);

        /**
         *
         * @param firstname
         * @param lastname
         * @param profilePicture
         * @param email
         */
        void saveUserDetails(String firstname, String lastname, String profilePicture, String email);

        /**
         *
         */
        void logoutFacebook();
    }

    interface UserActionListener{
        /**
         *
         */
        void login();

        /**
         *
         * @param username
         * @param firstname
         * @param lastname
         * @param email
         */
        void loginWithFacebook(String username, String firstname, String lastname, String email);
    }

}
