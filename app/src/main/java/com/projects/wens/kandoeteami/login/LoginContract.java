package com.projects.wens.kandoeteami.login;

/**
 * Created by senne on 23/02/2016.
 */
public interface LoginContract {
    interface view{
        /**
         * showing error with param message in Fragment
         * @param message
         */
        void showErrorMessage(String message);

        /**
         * succes message with param message in Fragment
         * @param message
         */
        void showSuccessMessage(String message);

        /**
         * on success showing sessions list activity
         */
        void showActivity();

        /**
         * save web token | shared preferences (token from rest api)
         * @param token
         */
        void saveToken(String token);

        /**
         * get username from view component -> rest call
         * @return
         */
        String getUsername();

        /**
         * get password from view component -> rest call
         * @return
         */
        String getPassword();

        /**
         * show progress of login -> during rest call
         */
        void showProgressLogin();


        /**
         * hide progress dialog of login --> on success
         */
        void stopProgress();


        /**
         * show username error with param message -> setError
         * @param message
         */
        void showUsernameError(String message);

        /**
         * show password error with param message -> setError
         * @param message
         */
        void showPasswordError(String message);

        /**
         * store user details -> Shared Preferences -> navigation drawer
         * @param firstname
         * @param lastname
         * @param profilePicture
         * @param email
         */
        void saveUserDetails(String firstname, String lastname, String profilePicture, String email);

        /**
         * logout with facebook
         */
        void logoutFacebook();
    }

    interface UserActionListener{
        /**
         * login action button clicked
         */
        void login();

        /**
         * creating user object with facebook rest call and login
         * @param username
         * @param firstname
         * @param lastname
         * @param email
         */
        void loginWithFacebook(String username, String firstname, String lastname, String email);
    }

}
