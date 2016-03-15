package com.projects.wens.kandoeteami.subthemes;

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
import com.projects.wens.kandoeteami.retrofit.service.SubThemaService;
import com.projects.wens.kandoeteami.subthemes.adapter.ListSubThemeAdapter;
import com.projects.wens.kandoeteami.subthemes.adapter.ListSubThemeListener;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senne on 14/03/2016.
 */
public class ListSubThemeFragment extends Fragment implements ListSubThemeContract.View {
    private static final String PREFS_NAME = "MyPrefs";

    private ListSubThemeContract.UserActionListener actionListener;

    private ListSubThemeAdapter subThemeAdapter;
    private SubThemaService service;

    public static ListSubThemeFragment fragment;

    //IMPLEMENTATIE VOOR DE RECYCLERVIEW
    ListSubThemeListener mItemListener = new ListSubThemeListener() {
        @Override
        public void onSubThemeClick(SubTheme clickTheme) {
            actionListener.openSubThemeDetail(clickTheme);
        }
    };

    public ListSubThemeFragment() {
    }

    public static Fragment newInstance(Boolean all, int organisationId) {
        fragment = new ListSubThemeFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("allSubThemes", all);
        bundle.putInt("themeId", organisationId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subThemeAdapter = new ListSubThemeAdapter(new ArrayList<SubTheme>(0), mItemListener, getActivity());
        service = ServiceGenerator.createService(SubThemaService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        actionListener = new ListSubThemePresenter(service, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_themes, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.themes_list);
        recyclerView.setAdapter(subThemeAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //SWIPE AND REFRESH
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Boolean all = fragment.getArguments().getBoolean("allSubThemes");
                if (!all) {
                    int id = fragment.getArguments().getInt("themeId");
                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                    String token = settings.getString("token", null);
                    actionListener.loadSubthemsForTheme(token, id);

                } else {
                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                    String token = settings.getString("token", null);
                    actionListener.loadSubthemes(token);
                }
            }
        });
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        Boolean all = fragment.getArguments().getBoolean("allSubThemes");
        if (!all){
            int id = fragment.getArguments().getInt("themeId");
            SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
            String token = settings.getString("token", null);
            actionListener.loadSubthemsForTheme(token, id);

        } else {
            SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
            String token = settings.getString("token", null);
            actionListener.loadSubthemes(token);
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
    public void showSubThemes(List<SubTheme> themes) {
        subThemeAdapter.replaceData(themes);
    }

    @Override
    public void showSubThemeDetail(Integer themeId) {
        //TODO: NAAR DE DETAIL PAGINA GAAN VAN EEN SUBTHEMA
    }

    @Override
    public void showErrorMessage(int status) {

    }
}
