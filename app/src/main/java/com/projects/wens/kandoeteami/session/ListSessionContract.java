package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.List;


public interface ListSessionContract {
    interface view{
        /**
         *
         * @param active
         */
        void setProgressIndicator(boolean active);

        /**
         *
         * @param sessions
         */
        void showSessions(List<SessionDTO> sessions);

        /**
         *
         * @param sessionId
         */
        void showSessionDetail(Integer sessionId);

        /**
         *
         * @param sessionId
         */
        void showSessionGame(Integer sessionId);

        /**
         *
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener{
        /**
         *
         * @param forceUpdate
         * @param token
         */
        void loadSessions(boolean forceUpdate, String token);

        /**
         *
         * @param clickSession
         */
        void openSessionDetail(SessionDTO clickSession);

        /**
         *
         * @param clickSession
         */
        void openSessionGame(SessionDTO clickSession);
    }
}
