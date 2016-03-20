package com.projects.wens.kandoeteami.subthemes;

import com.projects.wens.kandoeteami.organisation.data.ChildItem;
import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.retrofit.service.SubThemaService;
import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.session.data.SessionState;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;
import com.projects.wens.kandoeteami.user.data.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SubThemeDetailPresenter implements SubThemeDetailContract.UserActionListener {
    private final SubThemeDetailContract.View view;
    private final SubThemaService service;


    public SubThemeDetailPresenter(SubThemeDetailContract.View view, SubThemaService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void loadSubTheme(final String token,final int id) {
        service.getSubTheme("Bearer " + token, id, new Callback<SubTheme>() {
            @Override
            public void success(SubTheme subTheme, Response response) {
                getUsersSubTheme(subTheme,token,id);
                view.showSuccesMessage("Succesfully loaded subtheme");
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getMessage());

            }
        });
    }

    private void getUsersSubTheme(final SubTheme subTheme, String token, int id){
        final List<GroupItem> groupItems = new ArrayList<>();
        service.getSessionBySubThemeId("Bearer " + token, id, new Callback<List<SessionDTO>>() {
            @Override
            public void success(List<SessionDTO> sessionDTOs, Response response) {
                int inProgress = 0;
                for (SessionDTO sDTO: sessionDTOs){
                    GroupItem item = new GroupItem("SESSION " + sDTO.getSessionId() + " MEMBERS");
                    List<User> users = sDTO.getUsers();
                    for (User u: users){
                        ChildItem childItem = new ChildItem(u.getPerson().getFirstname(), u.getPerson().getLastname(), "MEMBER" , u.getProfilePicture());
                        item.addChildren(childItem);
                    }
                    if (sDTO.getState() == SessionState.IN_PROGRESS){
                        inProgress++;
                    }
                    groupItems.add(item);
                }
                int dtoSize = sessionDTOs.size();
                view.showSubTheme(subTheme, groupItems, inProgress, dtoSize);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getMessage());

            }
        });


    }
}
