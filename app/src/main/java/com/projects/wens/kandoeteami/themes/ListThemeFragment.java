package com.projects.wens.kandoeteami.themes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.projects.wens.kandoeteami.retrofit.service.ThemeService;


public class ListThemeFragment extends Fragment implements ListThemeContract.View {
    private static final String PREFS_NAME = "MyPrefs";

    private ListThemeContract.UserActionListener actionListener;

    private ThemeService service;

    public ListThemeFragment() {
    }

    public static Fragment newInstance() {
        return new ListThemeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(ThemeService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        actionListener = new ListThemePresenter(service, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO list replace
    }
}
