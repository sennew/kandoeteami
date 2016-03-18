package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by michaelkees on 18/03/16.
 */
public class ListSessionPresenter  implements ListSessionContract.UserActionListener{
    private final ListSessionContract.view view;
    private final SessionService service;
    private List<SessionDTO> list = new ArrayList<>();

    public ListSessionPresenter(ListSessionContract.view view, SessionService service){
        this.view = checkNotNull(view, "sessionView cannot be null!");
        this.service = service;
    }

    @Override
    public void loadSessions(boolean forceUpdate, String token) {
        if (view != null){
            view.setProgressIndicator(true);
        }
        service.getSessions("Bearer " + token, new Callback<List<SessionDTO>>() {
            @Override
            public void success(List<SessionDTO> sessionDTOs, Response response) {
                view.setProgressIndicator(false);
                view.showSessions(sessionDTOs);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getMessage());
            }
        });
    }

    @Override
    public void openSessionDetail(SessionDTO clickSession) {
        view.showSessionDetail(clickSession.getSessionId());
    }

    @Override
    public void openSessionGame(SessionDTO clickSession) {
        view.showSessionGame(clickSession.getSessionId());
    }
}
