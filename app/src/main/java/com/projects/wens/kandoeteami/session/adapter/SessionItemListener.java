package com.projects.wens.kandoeteami.session.adapter;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

/**
 * Created by michaelkees on 18/03/16.
 */
public interface SessionItemListener {
    void onSessionClick(SessionDTO clickSession);
    void onSessionDetailClick(SessionDTO clickSession);
}
