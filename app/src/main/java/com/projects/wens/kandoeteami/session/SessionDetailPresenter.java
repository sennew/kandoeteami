package com.projects.wens.kandoeteami.session;

import com.projects.wens.kandoeteami.organisation.data.ChildItem;
import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.user.data.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SessionDetailPresenter implements SessionDetailContract.UserActionListener {
    private final SessionDetailContract.View view;
    private final SessionService service;

    public SessionDetailPresenter(SessionDetailContract.View view, SessionService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void loadSession(String token, int sessionId) {
        final List<GroupItem> groupItems = new ArrayList<>();
        service.getSessionById("Bearer " + token, sessionId, new Callback<SessionDTO>() {
            @Override
            public void success(SessionDTO sessionDTO, Response response) {
                GroupItem item = new GroupItem("SESSION " + sessionDTO.getSessionId() + " MEMBERS");
                int dtoSize = 0;
                if (sessionDTO.getUsers() != null){
                    List<User> users = sessionDTO.getUsers();
                    for (User u : users) {
                        ChildItem child = new ChildItem(u.getPerson().getFirstname(), u.getPerson().getLastname(), "MEMBER", u.getProfilePicture());
                        item.addChildren(child);
                    }
                    groupItems.add(item);
                    dtoSize = users.size();
                }

                view.showSuccesMessage("Successfully loaded session");
                view.showSession(sessionDTO, groupItems, dtoSize, dtoSize);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getMessage());
            }
        });
    }
}
