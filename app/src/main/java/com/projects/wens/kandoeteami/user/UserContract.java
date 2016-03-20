package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.user.data.User;


public interface UserContract {

    interface View {

        /**
         * show user details with param user
         * @param user
         */
        void showUserDetails(User user);

        /**
         * get username validate
         * @return
         */
        String getUsername();

        /**
         * get firstname validate
         * @return
         */
        String getFirstName();

        /**
         * get lastname
         * @return
         */
        String getLastName();

        /**
         * get email
         * @return
         */
        String getEmail();

        /**
         * get address
         * @return
         */
        String getAddressStreet();

        /**
         * get number
         * @return
         */
        String getAddressNumber();

        /**
         * get zip
         * @return
         */
        String getAddressZip();

        /**
         * get city
         * @return
         */
        String getAddressCity();

        /**
         * getOldPass
         * @return
         */
        String getOldPassword();

        /**
         * get new pass
         * @return
         */
        String getNewPassword();

        /**
         * close dialog change password
         */
        void closeDialog();

        /**
         * show error message on failure calls
         * @param message
         */
        void showErrorMessage(String message);

        /**
         * show succes message on success calls
         * @param s
         */
        void showSuccesMessage(String s);
    }

    interface UserActionListener {
        /**
         * request loading user rest call GET
         * @param token
         */
        void loadUser(String token);

        /**
         * request updating user rest call POST
         * @param token
         * @param user
         */
        void updateUser(String token, User user);

        /**
         * request update user pass rest call POST
         * @param token
         * @param currentUser
         */
        void updatePassword(String token, User currentUser);
    }
}
