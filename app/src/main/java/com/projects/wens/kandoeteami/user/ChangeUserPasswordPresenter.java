package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.retrofit.service.UserService;
import com.projects.wens.kandoeteami.user.data.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ChangeUserPasswordPresenter implements ChangeUserPasswordContract.UserActionListener {
    private final ChangeUserPasswordContract.View view;
    private final UserService service;

    public ChangeUserPasswordPresenter(UserService service, ChangeUserPasswordContract.View view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void updatePassword(String token) {
        User user = new User();
        User cUser = view.getCurrentUser();

        if (validate()) {
            if (cUser != null) {
                user = cUser;
                user.setOldPassword(view.getOldPassword());
                user.setPassword(view.getNewPassword());
            }

            view.showProgress();
            service.updateCurrentUserPassword("Bearer " + token, user, new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    view.stopProgress();
                    view.showSucces();
                }

                @Override
                public void failure(RetrofitError error) {
                    view.stopProgress();
                    view.showErrorMessage("Failed to update user");
                }
            });
        }
    }

    private boolean validate() {
        if (view != null) {
            if (view.getOldPassword().isEmpty()) {
                view.showOldPasswordError("Empty old password");
                return false;
            }

            if (view.getNewPassword().isEmpty()) {
                view.showNewPasswordError("Empty new password");
                return false;
            }

            if (!view.getNewPassword().equals(view.getRetypeNewPassword())) {
                view.showNewPasswordError("Passwords do not match");
                return false;
            }
        }
        return true;
    }

    @Override
    public void loadUser(String token) {
        service.getCurrentUser("Bearer " + token, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                view.verifyUser(user);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage("Failed to load user");
            }
        });
    }
}
