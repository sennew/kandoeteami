package com.projects.wens.kandoeteami.session.adapter;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.session.data.SessionDTO;


public interface SessionItemListener {
    /**
     * on session click action , activity session game
     * @param clickSession
     */
    void onSessionClick(SessionDTO clickSession);

    /**
     * on session detail click , activity session detail
     * @param clickSession
     */
    void onSessionDetailClick(SessionDTO clickSession);
}
