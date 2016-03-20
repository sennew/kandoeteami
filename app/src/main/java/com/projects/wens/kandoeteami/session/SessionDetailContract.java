package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.List;

public interface SessionDetailContract {
    interface View {

        /**
         * show succes message on success rest call
         * @param message
         */
        void showSuccesMessage(String message);

        /**
         * show session on success rest call with param theme, groupitems , active sessions and total sessions
         * @param theme
         * @param items
         * @param activeSession
         * @param countSession
         */
        void showSession(SessionDTO theme, List<GroupItem> items, int activeSession, int countSession);

        /**
         * show error message on failure rest call
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener {
        /**
         * request rest call service , loading session with param header authorization token and sessionId
         * @param token
         * @param sessionId
         */
        void loadSession(String token, int sessionId);
    }
}
