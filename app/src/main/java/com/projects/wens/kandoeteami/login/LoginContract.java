package com.projects.wens.kandoeteami.login;

/**
 * Created by senne on 23/02/2016.
 */
public interface LoginContract {
    interface view{
        void showListOrganisation();
    }

    interface UserActionListener{
        void login();
    }

}
