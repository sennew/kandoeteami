package com.projects.wens.kandoeteami.register;

import android.content.SharedPreferences;


public interface RegisterContract {

    interface view {
        /**
         * show error message on failure rest calls
         * @param message
         */
        void showErrorMessage(String message);

        /**
         * show succes message on success rest calls
         * @param message
         */
        void showSuccessMessage(String message);

        /**
         * show login activity on success
         */
        void showLoginActivity();

        /**
         * getting email -> rest call
         * @return
         */
        String getEmail();

        /**
         * getting password -> rest call
         * @return
         */
        String getPassword();

        /**
         * getting retype password -> presenter validate password
         * @return
         */
        String getRetypePassword();

        /**
         * getting username -> rest call
         * @return
         */
        String getUsername();

        /**
         *  show error on view component email with param message
         * @param message
         */
        void showEmailError(String message);

        /**
         * show error on view component password with param message
         * @param message
         */
        void showPasswordError(String message);

        /**
         * show error on view component retype password with param message
         * @param message
         */
        void showRetypePasswordError(String message);

        /**
         * show error on view component username with param message
         * @param message
         */
        void showUsernameError(String message);

        /**
         * show progress dialog during register call
         */
        void showProgressRegister();

        /**
         * hide progress dialog on success
         */
        void hideProgress();
    }

    interface UserActionListener {
        /**
         * rest call register service with param preferences ( token & storing )
         * @param sharedPreferences
         */
       void register(SharedPreferences sharedPreferences);

    }
}
