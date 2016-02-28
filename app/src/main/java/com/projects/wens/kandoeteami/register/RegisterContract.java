package com.projects.wens.kandoeteami.register;

/**
 * Created by michaelkees on 28/02/16.
 */
public interface RegisterContract {

    interface view {
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
        void showLoginActivity();

        String getEmail();
        String getPassword();
        String getRetypePassword();
    }

    interface UserActionListener {
       void register();

    }
}
