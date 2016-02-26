package com.projects.wens.kandoeteami.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.login.data.AccesToken;
import com.projects.wens.kandoeteami.organisation.ListOrganisationActivity;
import com.projects.wens.kandoeteami.organisation.ListOrganisationPresenter;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.LoginService;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

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
        return new LoginFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO: HIER MOET DE PRESENTER AANGEMAAKT WORDEN

        service = ServiceGenerator.createService(LoginService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        mLoginActionListener = new LoginPresenter(this,service);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        mUsername = (EditText) root.findViewById(R.id.login_username);
        mPassword = (EditText) root.findViewById(R.id.login_password);
        mLogin_button = (Button) root.findViewById(R.id.btnLogin);
        mLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginActionListener.login();
            }
        });
        return root;
    }

    public void onResume(){
        super.onResume();
    }

    @Override
    public void showErrorMessage(String message) {
        //Crouton
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        //Crouton
        Crouton.makeText(getActivity(), message, Style.CONFIRM).show();
    }

    @Override
    public void showOrganisationsActivity() {
        Intent i = new Intent(getContext(), ListOrganisationActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @Override
    public void saveToken(String token) {
        // Storing token
        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", token);
        editor.apply();
    }



    @Override
    public String getUsername() {
        return mUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }
}
