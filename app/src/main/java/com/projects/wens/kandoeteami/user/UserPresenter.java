package com.projects.wens.kandoeteami.user;

import com.projects.wens.kandoeteami.retrofit.service.UserService;
import com.projects.wens.kandoeteami.themes.ListThemeContract;

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


}
