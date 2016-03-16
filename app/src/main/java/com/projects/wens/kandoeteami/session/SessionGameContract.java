package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.session.data.SessionDTO;


public interface SessionGameContract {

    interface View {
        void showSession(SessionDTO session);
        void showErrorMessage(String s);
    }

    interface UserActionListener {
        void loadSession(String token, int id);
    }
}
