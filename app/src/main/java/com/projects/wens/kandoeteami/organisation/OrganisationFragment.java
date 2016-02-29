package com.projects.wens.kandoeteami.organisation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by michaelkees on 29/02/16.
 */
public class OrganisationFragment extends Fragment implements OrganisationContract.View {
    public static final String PREFS_NAME = "MyPrefs";
    private OrganisationService service;
    private OrganisationContract.UserActionListener organisationActionListener;

    public static Fragment newInstance() {
        return new OrganisationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(OrganisationService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        organisationActionListener = new OrganisationPresenter(this, service);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);
        /*service.getOrganisation(token, 1, new Callback<Organisation>() {
            @Override
            public void success(Organisation organisation, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        //ROOT VIEW

    }

    @Override
    public int getOrganisationID() {
        return 0;
    }

    @Override
    public void showOrganisation() {

    }
}
