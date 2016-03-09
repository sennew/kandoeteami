package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.retrofit.service.UserService;
import com.projects.wens.kandoeteami.user.data.Address;
import com.projects.wens.kandoeteami.user.data.Person;
import com.projects.wens.kandoeteami.user.data.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by michaelkees on 01/03/16.
 */
public class UserPresenter implements UserContract.UserActionListener {

    private final UserContract.View view;
    private final UserService service;

    public UserPresenter(UserContract.View view, UserService service) {
        this.view = view;
        this.service = service;
    }


    @Override
    public void loadUser(String token) {
        service.getCurrentUser("Bearer " + token, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                view.showUserDetails(user);
            }

            @Override
            public void failure(RetrofitError error) {
                view.showErrorMessage(error.getMessage());
            }
        });
    }

    @Override
    public void updateUser(String token, User currentUser) {
        User user = new User();

            if (currentUser != null) {
                user.setUserId(currentUser.getUserId());
                user.setPassword(currentUser.getPassword());
                user.setUsername(currentUser.getUsername());
                user.setEmail(currentUser.getEmail());
                user.setPerson(
                        new Person(
                                view.getFirstName(),
                                view.getLastName(),
                                new Address(
                                        view.getAddressStreet(),
                                        view.getAddressNumber(),
                                        view.getAddressZip(),
                                        view.getAddressCity()))
                );
            }
            service.updateCurrentUser("Bearer " + token, user, new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    view.showUserDetails(user);
                    view.showSuccesMessage("Successfully updated user");
                }

                @Override
                public void failure(RetrofitError error) {
                    view.showErrorMessage("Error due updating user");
                }
            });

    }

    public void updatePassword(String token,User currentUser){
        User user = new User();

        if (currentUser != null){
            user = currentUser;
            user.setOldPassword(view.getOldPassword());
            user.setPassword(view.getNewPassword());
        }

        service.updateCurrentUserPassword("Bearer " + token, user, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                view.closeDialog();
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO: uitwerken error message
            }
        });

    }
}
