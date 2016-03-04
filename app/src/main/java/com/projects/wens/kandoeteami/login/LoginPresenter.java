package com.projects.wens.kandoeteami.login;

import com.projects.wens.kandoeteami.login.data.LoginDTO;
import com.projects.wens.kandoeteami.retrofit.service.LoginService;
import com.projects.wens.kandoeteami.retrofit.service.UserService;
import com.projects.wens.kandoeteami.user.data.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginPresenter implements LoginContract.UserActionListener {

    private final LoginContract.view view;
    private final LoginService service;
    private final UserService userService;

    public LoginPresenter(LoginContract.view view, LoginService service, UserService userservice) {
        this.view = view;
        this.service = service;
        this.userService = userservice;
    }


    @Override
    public void login() {
        final String username = view.getUsername();
        String password = view.getPassword();
        LoginDTO login = new LoginDTO(username, password);
        if (validate()) {
            view.showProgressLogin();
            service.login(login, new Callback<String>() {
                @Override
                public void success(String accesToken, Response response) {
                    view.showSuccessMessage("Login correct");
                    view.saveToken(accesToken);

                    userService.getCurrentUser(accesToken, new Callback<User>() {
                        @Override
                        public void success(User user, Response response) {
                            view.saveUserDetails(user.getUsername(), user.getProfilePicture());
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            view.showErrorMessage("Login failed");
                            view.stopProgress();
                        }
                    });

                    view.stopProgress();
                    view.showOrganisationsActivity();
                }

                @Override
                public void failure(RetrofitError error) {
                    view.showErrorMessage("Login failed.");
                    view.stopProgress();
                }
            });
        }





    }

    @Override
    public void loginWithFacebook(String username) {
        //
    }

    private boolean validate() {
        if (view.getUsername().isEmpty()) {
            view.showUsernameError("Username is empty");
            return false;
        }

        if (view.getPassword().isEmpty()) {
            view.showPasswordError("Password is empty!");
            return false;
        }

        return true;
    }
}
