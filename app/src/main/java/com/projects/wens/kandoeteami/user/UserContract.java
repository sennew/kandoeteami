package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.user.data.User;


public interface UserContract {

    interface View {

        /**
         *
         * @param user
         */
        void showUserDetails(User user);

        /**
         *
         * @return
         */
        String getUsername();

        /**
         *
         * @return
         */
        String getFirstName();

        /**
         *
         * @return
         */
        String getLastName();

        /**
         *
         * @return
         */
        String getEmail();

        /**
         *
         * @return
         */
        String getAddressStreet();

        /**
         *
         * @return
         */
        String getAddressNumber();

        /**
         *
         * @return
         */
        String getAddressZip();

        /**
         *
         * @return
         */
        String getAddressCity();

        /**
         *
         * @return
         */
        String getOldPassword();

        /**
         *
         * @return
         */
        String getNewPassword();

        /**
         *
         */
        void closeDialog();

        /**
         *
         * @param message
         */
        void showErrorMessage(String message);

        /**
         *
         * @param s
         */
        void showSuccesMessage(String s);
    }

    interface UserActionListener {
        /**
         *
         * @param token
         */
        void loadUser(String token);

        /**
         *
         * @param token
         * @param user
         */
        void updateUser(String token, User user);

        /**
         *
         * @param token
         * @param currentUser
         */
        void updatePassword(String token, User currentUser);
    }
}
