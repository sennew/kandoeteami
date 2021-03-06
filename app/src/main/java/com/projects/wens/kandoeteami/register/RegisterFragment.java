package com.projects.wens.kandoeteami.register;

import android.app.ProgressDialog;
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
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.login.LoginActivity;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.RegisterService;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class RegisterFragment extends Fragment implements RegisterContract.view {
    public static final String PREFS_NAME = "MyPrefs";
    private RegisterContract.UserActionListener registerListener;
    private RegisterService service;

    private ProgressDialog progressDialog;

    private Button btnRegister;
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etRetypePassword;
    private TextView tvLinkLogin;


    public RegisterFragment(){
        //Requires empty public contructor
    }

    public static RegisterFragment newInstance(){
        return new RegisterFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        service = ServiceGenerator.createService(RegisterService.class, getResources().getString(R.string.baseURL));
        registerListener = new RegisterPresenter(this , service);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        etUsername = (EditText) root.findViewById(R.id.reg_username);
        tvLinkLogin = (TextView) root.findViewById(R.id.link_login);
        etEmail = (EditText) root.findViewById(R.id.reg_email);
        etPassword = (EditText) root.findViewById(R.id.reg_password);
        etRetypePassword = (EditText) root.findViewById(R.id.reg_retypepassword);
        btnRegister = (Button) root.findViewById(R.id.register_button);
        final SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerListener.register(settings);
            }
        });
        tvLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginActivity();
            }
        });
        return root;
    }


    @Override
    public void showErrorMessage(String message) {
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Crouton.makeText(getActivity(), message, Style.CONFIRM).show();
    }

    @Override
    public void showLoginActivity() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("EMAIL", etEmail.getText().toString());
        i.putExtras(mBundle);
        startActivity(i);
        getActivity().finish();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public String getRetypePassword() {
        return etRetypePassword.getText().toString();
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString();
    }

    @Override
    public void showEmailError(String message) {
        etEmail.setError(message);
    }

    @Override
    public void showPasswordError(String message) {
        etPassword.setError(message);
    }

    @Override
    public void showRetypePasswordError(String message) {
        etRetypePassword.setError(message);
    }

    @Override
    public void showUsernameError(String message) {
        etUsername.setError(message);
    }

    @Override
    public void showProgressRegister() {
        progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating account...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }


}
