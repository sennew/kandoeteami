package com.projects.wens.kandoeteami.login;

import com.projects.wens.kandoeteami.login.data.AccesToken;
import com.projects.wens.kandoeteami.retrofit.service.LoginService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by senne on 23/02/2016.
 */
public class LoginPresenter implements LoginContract.UserActionListener {

    private final LoginContract.view view;
    private final LoginService service;

    public LoginPresenter(LoginContract.view view, LoginService service) {
        this.view = view;
        this.service = service;
    }


    @Override
    public void login() {
        String username = view.getUsername();
        String password = view.getPassword();
        service.login(username, password, new Callback<AccesToken>() {
            @Override
            public void success(AccesToken accesToken, Response response) {
                view.showSuccessMessage("Login correct");
                view.saveToken(accesToken.getAcces_token());
                //TODO sessionManagement
                view.showOrganisationsActivity();
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage("Login failed.");
            }
        });
    }
}
