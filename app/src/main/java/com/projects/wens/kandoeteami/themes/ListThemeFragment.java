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
import com.projects.wens.kandoeteami.retrofit.service.ThemeService;
import com.projects.wens.kandoeteami.themes.adapter.ThemeAdapter;
import com.projects.wens.kandoeteami.themes.adapter.ThemeItemListener;
import com.projects.wens.kandoeteami.themes.data.Theme;

import java.util.ArrayList;
import java.util.List;


public class ListThemeFragment extends Fragment implements ListThemeContract.View {
    private static final String PREFS_NAME = "MyPrefs";

    private ListThemeContract.UserActionListener actionListener;

    private ThemeAdapter themeAdapter;
    private ThemeService service;

    public static ListThemeFragment fragment;

    //IMPLEMENTATIE VOOR DE RECYCLERVIEW
    ThemeItemListener mItemListener = new ThemeItemListener() {
        @Override
        public void onThemeClick(Theme clickTheme) {
            actionListener.openThemeDetail(clickTheme);
        }
    };

    public ListThemeFragment() {
    }

    public static Fragment newInstance(Boolean all, int organisationId) {
        fragment = new ListThemeFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("allThemes", all);
        bundle.putInt("organisationId", organisationId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeAdapter = new ThemeAdapter(new ArrayList<Theme>(0), mItemListener, getActivity());
        service = ServiceGenerator.createService(ThemeService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        actionListener = new ListThemePresenter(service, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_themes, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.themes_list);
        recyclerView.setAdapter(themeAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //SWIPE AND REFRESH
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Boolean all = fragment.getArguments().getBoolean("allThemes");
                if (!all){
                    int id = fragment.getArguments().getInt("organisationId");
                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                    String token = settings.getString("token", null);
                    actionListener.loadThemesForOrganisation(token,id);

                } else {
                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                    String token = settings.getString("token", null);
                    actionListener.loadThemes(token);
                }
            }
        });
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        Boolean all = fragment.getArguments().getBoolean("allThemes");
        if (!all){
            int id = fragment.getArguments().getInt("organisationId");
            SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
            String token = settings.getString("token", null);
            actionListener.loadThemesForOrganisation(token,id);

        } else {
            SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
            String token = settings.getString("token", null);
            actionListener.loadThemes(token);
        }
    }

    @Override
    public void setProgressIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }

        final SwipeRefreshLayout srl = (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });

    }

    @Override
    public void showThemes(List<Theme> themes) {
        themeAdapter.replaceData(themes);
    }

    @Override
    public void showThemeDetail(Integer themeId) {
        //INTENT
    }

    @Override
    public void showErrorMessage(int status) {

    }
}
