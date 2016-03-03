package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.user.data.User;

/**
 * Created by michaelkees on 03/03/16.
 */
public interface ChangeUserPasswordContract {
    interface View {
        String getOldPassword();
        String getNewPassword();
        String getRetypeNewPassword();
        void showSucces();

        void verifyUser(User user);

        void showErrorMessage(String s);

        void stopProgress();

        void showProgress();

        User getCurrentUser();

        void showOldPasswordError(String s);
        void showNewPasswordError(String s);


    }
    interface UserActionListener{
        void updatePassword(String token);

        void loadUser(String token);
    }
}
