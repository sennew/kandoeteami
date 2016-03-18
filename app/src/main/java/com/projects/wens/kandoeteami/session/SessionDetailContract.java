package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.List;

/**
 * Created by senne on 12/03/2016.
 */
public interface SessionDetailContract {
    interface View {
        void showSuccesMessage(String message);
        void showSession(SessionDTO theme, List<GroupItem> items, int activeSession, int countSession);
        void showErrorMessage(String message);
    }

    interface UserActionListener {
        void loadSession(String token, int sessionId);
    }
}
