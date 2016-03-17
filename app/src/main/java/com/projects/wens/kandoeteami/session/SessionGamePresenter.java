package com.projects.wens.kandoeteami.session;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.projects.wens.kandoeteami.organisation.OrganisationContract;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by michaelkees on 16/03/16.
 */
public class SessionGamePresenter implements SessionGameContract.UserActionListener {

    private final SessionGameContract.View view;
    private final SessionService service;

    public SessionGamePresenter(SessionGameContract.View view, SessionService service) {
        this.view = view;
        this.service = service;
    }


    @Override
    public void loadSession(String token, int id) {
        service.getSessionById("Bearer " + token, id, new Callback<SessionDTO>() {
            @Override
            public void success(SessionDTO sessionDTO, Response response) {
                Log.e("","SIZE: "+sessionDTO.getCards().size());
                view.showSession(sessionDTO);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage("Failed to load session");
            }
        });
    }
}
