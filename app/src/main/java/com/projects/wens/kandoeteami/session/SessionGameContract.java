package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.session.data.SessionDTO;


public interface SessionGameContract {

    interface View {
        /**
         * show session in view with param session object
         * @param session
         */
        void showSession(SessionDTO session);

        /**
         * show error message on failure rest call
         * @param s
         */
        void showErrorMessage(String s);

        /**
         * show error message on failure can play
         */
        void showNotCurrentPlayerError();

        /**
         * show succes message on success can play, make move, loading session
         * @param s
         */
        void showSuccesMessage(String s);
    }

    interface UserActionListener {
        /**
         * request rest call. GET sessions with param token and id
         * @param token
         * @param id
         */
        void loadSession(String token, int id);

        /**
         * request: can play ? make move : error message
         * @param token
         * @param cardId
         */
        void canMakeCardMove(String token, int cardId);

    }
}
