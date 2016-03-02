package com.projects.wens.kandoeteami.themes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.projects.wens.kandoeteami.retrofit.service.ThemeService;
import com.projects.wens.kandoeteami.themes.adapter.ThemeAdapter;


public class ListThemeFragment extends Fragment implements ListThemeContract.View {
    private static final String PREFS_NAME = "MyPrefs";

    private ListThemeContract.UserActionListener actionListener;

    private ThemeService service;

    private ThemeAdapter themeAdapter;

    public ListThemeFragment() {
    }

    public static Fragment newInstance() {
        return new ListThemeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeAdapter = new ThemeAdapter();
        service = ServiceGenerator.createService(ThemeService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        actionListener = new ListThemePresenter(service, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_themes, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.themes_list);

        //recyclerView.setAdapter(themeAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //SWIPE AND REFRESH
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Get preferences token
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                String token = settings.getString("token", null);
                //TODO load organisations
                // actionListener.loadOrganisations(true, token);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        //Restore preferences
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);

        //actionListener.loadThemes(...)
        //TODO list load
    }
}
