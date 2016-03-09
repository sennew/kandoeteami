package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.user.data.User;

/**
 * Created by michaelkees on 01/03/16.
 */
public interface UserContract {

    interface View {
        void showUserDetails(User user);
        String getUsername();
        String getFirstName();
        String getLastName();
        String getEmail();
        String getAddressStreet();
        String getAddressNumber();
        String getAddressZip();
        String getAddressCity();
        String getOldPassword();
        String getNewPassword();
        void closeDialog();
        void showErrorMessage(String message);
        void showSuccesMessage(String s);
    }

    interface UserActionListener {
        void loadUser(String token);
        void updateUser(String token, User user);
        void updatePassword(String token, User currentUser);
    }
}
