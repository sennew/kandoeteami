package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.List;

public interface SessionDetailContract {
    interface View {

        /**
         *
         * @param message
         */
        void showSuccesMessage(String message);

        /**
         *
         * @param theme
         * @param items
         * @param activeSession
         * @param countSession
         */
        void showSession(SessionDTO theme, List<GroupItem> items, int activeSession, int countSession);

        /**
         *
         * @param message
         */
        void showErrorMessage(String message);
    }

    interface UserActionListener {
        /**
         *
         * @param token
         * @param sessionId
         */
        void loadSession(String token, int sessionId);
    }
}
