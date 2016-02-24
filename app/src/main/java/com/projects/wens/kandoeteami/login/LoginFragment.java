package com.projects.wens.kandoeteami.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.retrofit.service.LoginService;

/**
 * Created by senne on 23/02/2016.
 */
public class LoginFragment extends Fragment implements LoginContract.view {
    private LoginContract.UserActionListener mLoginActionListener;
    private LoginService service;

    //DECLARATION COMPONENTS
    private Button mLogin_button;
    private EditText mUsername;
    private EditText mPassword;


    public LoginFragment(){
        //Requires empty public contructor
    }

    public static LoginFragment newInstance(){
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO: HIER MOET DE PRESENTER AANGEMAAKT WORDEN
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        mUsername = (EditText) root.findViewById(R.id.login_username);
        mPassword = (EditText) root.findViewById(R.id.login_password);
        mLogin_button = (Button) root.findViewById(R.id.login_button);
        return root;
    }

    @Override
    public void showListOrganisation() {
        //TODO: INTENT AANMAKEN VOOR DE ORGANISATION TE LATEN ZIEN
    }
}
