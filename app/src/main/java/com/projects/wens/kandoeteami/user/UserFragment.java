package com.projects.wens.kandoeteami.user;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.UserService;
import com.projects.wens.kandoeteami.user.data.User;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment implements UserContract.View{
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
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
    private Button btnEditUser;
    private Dialog changePasswordDialog;
    private EditText dialogOldPassword;
    private EditText dialogNewPassword;
    private EditText dialogRetypePassword;
    private Button dialogBtnCancel;
    private Button dialogBtnOk;

    public static Fragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(UserService.class, getResources().getString(R.string.baseURL));
        actionListener = new UserPresenter(this, service);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);
        actionListener.loadUser(token);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        imgUser = (CircleImageView) getActivity().findViewById(R.id.user_img);
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
        btnEditUser = (Button) root.findViewById(R.id.user_edit_button);

        changePasswordDialog = new Dialog(getContext());
        changePasswordDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        changePasswordDialog.setContentView(inflater.inflate(R.layout.dialog_change_password, null));

        dialogNewPassword = (EditText) changePasswordDialog.findViewById(R.id.user_new_password);
        dialogOldPassword = (EditText) changePasswordDialog.findViewById(R.id.user_old_password);
        dialogRetypePassword = (EditText) changePasswordDialog.findViewById(R.id.user_retype_password);

        dialogBtnCancel = (Button) changePasswordDialog.findViewById(R.id.dialog_button_cancel);
        dialogBtnOk = (Button) changePasswordDialog.findViewById(R.id.dialog_button_ok);

        dialogBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePasswordDialog.cancel();
            }
        });

        dialogBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                String token = settings.getString("token", null);
                actionListener.updatePassword(token, currentUser);
            }
        });


        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsername.setEnabled(true);
                etFirstName.setEnabled(true);
                etLastName.setEnabled(true);
                etEmail.setEnabled(true);
                etAddressStreet.setEnabled(true);
                etAddressNumber.setEnabled(true);
                etAddressCity.setEnabled(true);
                etAddressZip.setEnabled(true);
                btnEditUser.setVisibility(View.INVISIBLE);
                btnChangeUser.setVisibility(View.VISIBLE);

            }
        });


        btnChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsername.setEnabled(false);
                etFirstName.setEnabled(false);
                etLastName.setEnabled(false);
                etEmail.setEnabled(false);
                etAddressStreet.setEnabled(false);
                etAddressNumber.setEnabled(false);
                etAddressCity.setEnabled(false);
                etAddressZip.setEnabled(false);
                btnEditUser.setVisibility(View.VISIBLE);
                btnChangeUser.setVisibility(View.INVISIBLE);
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                String token = settings.getString("token", null);
                actionListener.updateUser(token, currentUser);
            }
        });







        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changePasswordDialog.show();
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
        if (user.getProfilePicture() != null){
            Picasso.with(this.getContext()).load(PICASSO_BASEURL + user.getProfilePicture()).into(imgUser);
        }

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
    public String getOldPassword() {
        return dialogOldPassword.getText().toString();
    }

    @Override
    public String getNewPassword() {
        return dialogNewPassword.getText().toString();
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

    public void closeDialog(){
        changePasswordDialog.cancel();
    }
}
