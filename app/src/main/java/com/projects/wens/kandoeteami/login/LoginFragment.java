package com.projects.wens.kandoeteami.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.ListOrganisationActivity;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.LoginService;
import com.projects.wens.kandoeteami.retrofit.service.UserService;

import java.util.Arrays;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class LoginFragment extends Fragment implements LoginContract.view {
    private LoginContract.UserActionListener mLoginActionListener;
    private LoginService service;
    private UserService userService;
    public static final String PREFS_NAME = "MyPrefs";
    private ProgressDialog progressDialog;

    //DECLARATION COMPONENTS
    private Button mLogin_button;
    private LoginButton loginButtonFB;
    private EditText mUsername;
    private EditText mPassword;


    //FACEBOOK
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private String fBUserName;
    private String fBEmail;
    private String fBFirstName;
    private String fBLastName;


    public LoginFragment() {
        //Requires empty public contructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            String email = getActivity().getIntent().getExtras().getString("EMAIL");
            mUsername.setText(email);
        }

        service = ServiceGenerator.createService(LoginService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        userService = ServiceGenerator.createService(UserService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        mLoginActionListener = new LoginPresenter(this, service, userService);

        // FACEBOOK : Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getActivity());
        // especially, if you're using Facebook UI elements. --> LoginButton

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        accessTokenTracker.startTracking();
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        //FACEBOOK  provide factory here --> due error 'please use provided factory'
        callbackManager = CallbackManager.Factory.create();

        //SharedPreferences om user
        String username = getActivity().getSharedPreferences(PREFS_NAME, 0).getString("username", null);

        mUsername = (EditText) root.findViewById(R.id.login_username);
        mUsername.setText(username);
        mPassword = (EditText) root.findViewById(R.id.login_password);
        mLogin_button = (Button) root.findViewById(R.id.btnLogin);
        mLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginActionListener.login();
            }
        });
        loginButtonFB = (LoginButton) root.findViewById(R.id.login_button_facebook);

        List<String> permissionNeeds = Arrays.asList("public_profile", "email",
                "user_birthday");
        loginButtonFB.setReadPermissions(permissionNeeds);

        //LoginManager.getInstance().logInWithReadPermissions(this, permissionNeeds);

        Log.d("FB", "Facebook view created succesfully!");

        loginButtonFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("FB", "Facebook login successfully!");
                Profile profile = Profile.getCurrentProfile();
                //profile.getProfilePictureUri(50, 50);
                //Toast.makeText(getActivity(), profile.getProfilePictureUri(50, 50).getPath(), Toast.LENGTH_SHORT).show();
                fBFirstName = profile.getFirstName();
                fBLastName = profile.getMiddleName() + " " + profile.getLastName();
                fBUserName = fBFirstName + fBLastName + "_facebook";
                //final String[] emailProps = {""};
                /*GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Application code
                                try {
                                    emailProps[0] = object.getString("email");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields",
                        "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();*/

                //fBEmail = emailProps[0];
                fBEmail = fBFirstName.toLowerCase() + "." + fBLastName.toLowerCase() + "@student.kdg.be";
                mLoginActionListener.loginWithFacebook(fBFirstName,fBLastName,fBUserName,fBEmail);
            }


            @Override
            public void onCancel() {
                Log.d("FB", "Facebook login canceled!");
                showErrorMessage("canceled!");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FB", "Facebook login failed!");
                showErrorMessage(error.getMessage());
            }
        });



        return root;
    }

    public void onResume() {
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
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", token);

        // Commit the edits!
        editor.commit();
    }


    @Override
    public String getUsername() {
        return mUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }

    @Override
    public void showProgressLogin() {
        progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }

    @Override
    public void stopProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showUsernameError(String message) {
        mUsername.setError(message);
    }

    @Override
    public void showPasswordError(String message) {
        mPassword.setError(message);
    }

    @Override
    public void saveUserDetails(String firstname, String lastname, String profilepicture, String email) {
        // Storing token
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("firstname", firstname);
        editor.putString("lastname", lastname);
        editor.putString("profilepicture", profilepicture);
        editor.putString("email", email);

        // Commit the edits!
        editor.commit();
    }

    @Override
    public String getFBUserName() {
        return fBUserName;
    }

    @Override
    public String getFBEmail() {
        return fBEmail;
    }

    @Override
    public String getFBFirstName() {
        return fBFirstName;
    }

    @Override
    public String getFBLastName() {
        return fBLastName;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("FB", "Facebook onactivity result!");
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();

    }
}