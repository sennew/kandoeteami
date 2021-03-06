package com.projects.wens.kandoeteami.login;

import com.facebook.CallbackManager;
import com.projects.wens.kandoeteami.login.data.LoginDTO;
import com.projects.wens.kandoeteami.retrofit.service.LoginService;
import com.projects.wens.kandoeteami.retrofit.service.UserService;
import com.projects.wens.kandoeteami.user.data.Address;
import com.projects.wens.kandoeteami.user.data.Person;
import com.projects.wens.kandoeteami.user.data.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginPresenter implements LoginContract.UserActionListener {

    private final LoginContract.view view;
    private final LoginService service;
    private final UserService userService;
    //FACEBOOK  provide factory here --> due error 'please use provided factory'
    private final CallbackManager manager;



    public LoginPresenter(LoginContract.view view, LoginService service, UserService userservice) {
        this.view = view;
        this.service = service;
        this.userService = userservice;
        this.manager = CallbackManager.Factory.create();
    }


    @Override
    public void login() {
        final String username = view.getUsername();
        String password = view.getPassword();
        LoginDTO login = new LoginDTO(username, password);
        final String token;
        if (validate()) {
            service.login(login, new Callback<String>() {
                @Override
                public void success(String accesToken, Response response) {
                    view.saveToken(accesToken);
                    saveUserDetails(accesToken);
                    view.showProgressLogin();
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
    public void loginWithFacebook(String username, String firstname, String lastname, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPerson(new Person(firstname, lastname, new Address("", "", "", "")));
        user.setFacebookAccount(true);
        view.showProgressLogin();
        service.loginFacebook(user, new Callback<String>() {
            @Override
            public void success(String accesToken, Response response) {
                view.saveToken(accesToken);
                view.stopProgress();
                saveUserDetails(accesToken);
                view.showActivity();
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage("Failed to login with facebook");
                view.logoutFacebook();
                view.stopProgress();
            }
        });
    }

    private void saveUserDetails(String accesToken){
        view.showProgressLogin();
        userService.getCurrentUser("Bearer " + accesToken, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                view.showSuccessMessage("Login correct");
                view.saveUserDetails(user.getPerson().getFirstname(), user.getPerson().getLastname(), user.getProfilePicture(), user.getEmail());
                view.stopProgress();
                view.showActivity();
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage("Login failed.");
                view.stopProgress();
            }
        });
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
