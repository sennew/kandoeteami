package com.projects.wens.kandoeteami.session;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.projects.wens.kandoeteami.organisation.OrganisationContract;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.retrofit.service.UserService;
import com.projects.wens.kandoeteami.session.data.CurrentMove;
import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.user.data.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SessionGamePresenter implements SessionGameContract.UserActionListener {

    private final SessionGameContract.View view;
    private final SessionService service;
    private final UserService userService;
    private int sessionId;
    private String token;

    public SessionGamePresenter(SessionGameContract.View view, SessionService service, UserService userService) {
        this.view = view;
        this.service = service;
        this.userService=userService;
    }

    @Override
    public void loadSession(String token, int id) {
        service.getSessionById("Bearer " + token, id, new Callback<SessionDTO>() {
            @Override
            public void success(SessionDTO sessionDTO, Response response) {
                view.showSession(sessionDTO);
                sessionId = sessionDTO.getSessionId();
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage("Failed to load session");
            }
        });
    }

    @Override
    public void canMakeCardMove(final String token, final int cardId) {
        this.token = token;

        service.checkCanUserPlay("Bearer " + token, sessionId, new Callback<Boolean>() {
            @Override
            public void success(Boolean aBoolean, Response response) {
                if(aBoolean){
                    CurrentMove move = new CurrentMove(token, sessionId, cardId);
                    service.makeMove("Bearer " + token, move, new Callback<SessionDTO>() {
                        @Override
                        public void success(SessionDTO sessionDTO, Response response) {
                            view.showSession(sessionDTO);
                            view.showSuccesMessage("Successfully voted");
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            view.showErrorMessage(error.getMessage());
                        }
                    });
                } else {
                    view.showErrorMessage("Not your turn");
                }
            }
            @Override
            public void failure(RetrofitError error) {
                view.showNotCurrentPlayerError();
            }
        });
    }
}
