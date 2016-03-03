package com.projects.wens.kandoeteami.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.UserService;
import com.projects.wens.kandoeteami.user.data.User;

import de.hdodenhof.circleimageview.CircleImageView;
import de.keyboardsurfer.android.widget.crouton.Crouton;


public class ChangeUserPasswordFragment extends Fragment implements ChangeUserPasswordContract.View {
    public static final String PREFS_NAME = "MyPrefs";
    private UserService service;
    private ChangeUserPasswordContract.UserActionListener actionListener;
    private User currentUser;
    private ProgressDialog progressDialog;

    private EditText etOldPassword;
    private EditText etNewPassword;
    private EditText etRetypeNewPassword;
    private Button btnSubmitPassword;

    public static Fragment newInstance() {
        return new ChangeUserPasswordFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(UserService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        actionListener = new ChangeUserPasswordPresenter(service, this);

    }

    @Override
    public void onResume() {
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);
        actionListener.loadUser(token);
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_change_password_user, container, false);
        etOldPassword = (EditText) root.findViewById(R.id.change_old_password);
        etNewPassword = (EditText) root.findViewById(R.id.change_new_password);
        etRetypeNewPassword = (EditText) root.findViewById(R.id.change_retype_new_password);
        btnSubmitPassword = (Button) root.findViewById(R.id.change_password_button);
        btnSubmitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                String token = settings.getString("token", null);
                actionListener.updatePassword(token);
            }
        });
        return root;
    }


    @Override
    public String getOldPassword() {
        return etOldPassword.getText().toString();
    }

    @Override
    public String getNewPassword() {
        return etNewPassword.getText().toString();
    }

    @Override
    public String getRetypeNewPassword() {
        return etRetypeNewPassword.getText().toString();
    }

    @Override
    public void showSucces() {
        getActivity().finish();
    }

    @Override
    public void verifyUser(User user) {
        this.currentUser = user;
    }

    @Override
    public void showErrorMessage(String s) {
        Snackbar snackbar = Snackbar.make(getView(), s, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void stopProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Updating...");
        progressDialog.show();
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void showOldPasswordError(String s) {
        etOldPassword.setError(s);
    }

    @Override
    public void showNewPasswordError(String s) {
        etNewPassword.setError(s);
    }
}
