package com.projects.wens.kandoeteami.register;

import android.widget.EditText;

/**
 * Created by michaelkees on 28/02/16.
 */
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

        void showPasswordError(String message);

        void showRetypePasswordError(String message);

        void showUsernameError(String message);
    }

    interface UserActionListener {
       void register();

    }
}
