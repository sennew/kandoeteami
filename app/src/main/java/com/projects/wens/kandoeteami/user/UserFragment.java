package com.projects.wens.kandoeteami.user;

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
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by michaelkees on 01/03/16.
 */
public class UserFragment extends Fragment implements UserContract.View{

    public static final String PREFS_NAME = "MyPrefs";
    private UserService service;
    private UserContract.UserActionListener actionListener;

    private User currentUser;

    private CircleImageView imgUser;
    private EditText etUsername;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etAddressStreet;
    private EditText etAddressNumber;
    private EditText etAddressZip;
    private EditText etAddressCity;

    private Button btnChangeUser;
    private Button btnChangePassword;



    public static Fragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(UserService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        actionListener = new UserPresenter(this, service);
    }

    @Override
    public void onResume() {
        super.onResume();
        //load user again
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);
        actionListener.loadUser(token);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        imgUser = (CircleImageView) root.findViewById(R.id.user_img);
        etUsername = (EditText) root.findViewById(R.id.user_username);
        etFirstName = (EditText) root.findViewById(R.id.user_firstname);
        etLastName = (EditText) root.findViewById(R.id.user_lastname);
        etEmail = (EditText) root.findViewById(R.id.user_email);
        etAddressStreet = (EditText) root.findViewById(R.id.user_address_street);
        etAddressNumber = (EditText) root.findViewById(R.id.user_address_number);
        etAddressCity = (EditText) root.findViewById(R.id.user_address_city);
        etAddressZip = (EditText) root.findViewById(R.id.user_address_zip);

        btnChangeUser = (Button) root.findViewById(R.id.user_change_button);
        btnChangePassword = (Button) root.findViewById(R.id.user_change_password_button);

        btnChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                String token = settings.getString("token", null);
                actionListener.updateUser(token, currentUser);
            }
        });

        return root;
    }

    @Override
    public void showUserDetails(User user) {
        this.currentUser = user;
        etUsername.setText(user.getUsername());
        etEmail.setText(user.getEmail());
        etFirstName.setText(user.getPerson().getFirstname());
        etLastName.setText(user.getPerson().getLastname());
        etAddressStreet.setText(user.getPerson().getAddress().getStreet());
        etAddressNumber.setText(user.getPerson().getAddress().getNumber());
        etAddressCity.setText(user.getPerson().getAddress().getCity());
        etAddressZip.setText(user.getPerson().getAddress().getZip());
    }

    @Override
    public void showChangePasswordActivity() {
        //TODO change password activity
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString();
    }

    @Override
    public String getFirstName() {
        return etFirstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return etLastName.getText().toString();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getAddressStreet() {
        return etAddressStreet.getText().toString();
    }

    @Override
    public String getAddressNumber() {
        return etAddressNumber.getText().toString();
    }

    @Override
    public String getAddressZip() {
        return etAddressZip.getText().toString();
    }

    @Override
    public String getAddressCity() {
        return etAddressCity.getText().toString();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showSuccesMessage(String s) {
        Snackbar snackbar = Snackbar.make(getView(), s, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
