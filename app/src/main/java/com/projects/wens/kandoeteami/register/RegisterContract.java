package com.projects.wens.kandoeteami.register;

import android.content.SharedPreferences;


public interface RegisterContract {

    interface view {
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
        void showLoginActivity();

        /**
         *
         * @return
         */
        String getEmail();

        /**
         *
         * @return
         */
        String getPassword();

        /**
         *
         * @return
         */
        String getRetypePassword();

        String getUsername();

        /**
         *
         * @param message
         */
        void showEmailError(String message);

        /**
         *
         * @param message
         */
        void showPasswordError(String message);

        /**
         *
         * @param message
         */
        void showRetypePasswordError(String message);

        /**
         *
         * @param message
         */
        void showUsernameError(String message);

        /**
         *
         */
        void showProgressRegister();

        /**
         *
         */
        void hideProgress();
    }

    interface UserActionListener {
        /**
         *
         * @param sharedPreferences
         */
       void register(SharedPreferences sharedPreferences);

    }
}
