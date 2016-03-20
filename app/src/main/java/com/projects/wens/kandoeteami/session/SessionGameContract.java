package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.session.data.SessionDTO;


public interface SessionGameContract {

    interface View {
        /**
         *
         * @param session
         */
        void showSession(SessionDTO session);

        /**
         *
         * @param s
         */
        void showErrorMessage(String s);

        /**
         *
         */
        void showNotCurrentPlayerError();

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
         * @param id
         */
        void loadSession(String token, int id);

        /**
         *
         * @param token
         * @param cardId
         */
        void canMakeCardMove(String token, int cardId);

    }
}
