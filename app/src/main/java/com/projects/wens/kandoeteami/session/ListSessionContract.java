package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.List;


public interface ListSessionContract {
    interface view{
        /**
         * set progress loading sessions
         * @param active
         */
        void setProgressIndicator(boolean active);

        /**
         * show sessions in view with param sessions
         * @param sessions
         */
        void showSessions(List<SessionDTO> sessions);

        /**
         * show session detail activity of session (findbyid)
         * @param sessionId
         */
        void showSessionDetail(Integer sessionId);

        /**
         * show session game activity of session (findbyid)
         * @param sessionId
         */
        void showSessionGame(Integer sessionId);

        /**
         * show error message -> rest call failure
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener{
        /**
         * loading sessions with token. rest call
         * @param forceUpdate
         * @param token
         */
        void loadSessions(boolean forceUpdate, String token);

        /**
         * request open session detail
         * @param clickSession
         */
        void openSessionDetail(SessionDTO clickSession);

        /**
         * request open session game
         * @param clickSession
         */
        void openSessionGame(SessionDTO clickSession);
    }
}
