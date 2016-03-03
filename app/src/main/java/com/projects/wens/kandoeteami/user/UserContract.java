package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.user.data.User;

/**
 * Created by michaelkees on 01/03/16.
 */
public interface UserContract {

    interface View {
        void showUserDetails(User user);
        void showChangePasswordActivity();
        String getUsername();
        String getFirstName();
        String getLastName();
        String getEmail();
        String getAddressStreet();
        String getAddressNumber();
        String getAddressZip();
        String getAddressCity();

        void showErrorMessage(String message);

        void showSuccesMessage(String s);
    }

    interface UserActionListener {
        void loadUser(String token);
        void updateUser(String token, User user);
    }
}
