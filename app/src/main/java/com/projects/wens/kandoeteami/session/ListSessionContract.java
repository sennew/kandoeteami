package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.List;

/**
 * Created by michaelkees on 18/03/16.
 */
public interface ListSessionContract {
    interface view{
        void setProgressIndicator(boolean active);
        void showSessions(List<SessionDTO> sessions);
        void showSessionDetail(Integer sessionId);
        void showSessionGame(Integer sessionId);
        void showErrorMessage(String message);
    }

    interface UserActionListener{
        void loadSessions(boolean forceUpdate, String token);

        void openSessionDetail(SessionDTO clickSession);

        void openSessionGame(SessionDTO clickSession);
    }
}
