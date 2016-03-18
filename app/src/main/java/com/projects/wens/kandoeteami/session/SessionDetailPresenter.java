package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by michaelkees on 18/03/16.
 */
public class SessionDetailPresenter implements SessionDetailContract.UserActionListener {
    private final SessionDetailContract.View view;
    private final SessionService service;

    public SessionDetailPresenter(SessionDetailContract.View view, SessionService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void loadSession(String token, int sessionId) {
        service.getSessionById("Bearer " + token, sessionId, new Callback<SessionDTO>() {
            @Override
            public void success(SessionDTO sessionDTO, Response response) {
                //view.showSession(sessionDTO);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getMessage());
            }
        });
    }
}
