package com.projects.wens.kandoeteami.session.adapter;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.session.data.SessionDTO;


public interface SessionItemListener {
    /**
     *
     * @param clickSession
     */
    void onSessionClick(SessionDTO clickSession);

    /**
     *
     * @param clickSession
     */
    void onSessionDetailClick(SessionDTO clickSession);
}
