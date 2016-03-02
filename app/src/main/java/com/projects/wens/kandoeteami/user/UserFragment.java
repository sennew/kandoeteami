package com.projects.wens.kandoeteami.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.UserService;

/**
 * Created by michaelkees on 01/03/16.
 */
public class UserFragment extends Fragment implements UserContract.View{

    public static final String PREFS_NAME = "MyPrefs";
    private UserService service;
    private UserContract.UserActionListener actionListener;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);


        return root;
    }
}
