package com.projects.wens.kandoeteami.session;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.ListOrganisationContract;
import com.projects.wens.kandoeteami.organisation.ListOrganisationPresenter;
import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.session.adapter.SessionContentAdapter;
import com.projects.wens.kandoeteami.session.adapter.SessionItemListener;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.ArrayList;
import java.util.List;


public class ListSessionFragment extends Fragment implements ListSessionContract.view {
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    public static final String PREFS_NAME = "MyPrefs";
    private ListSessionContract.UserActionListener actionListener;
    private SessionContentAdapter mSessionAdapter;
    private SessionService service;

    public ListSessionFragment() {
        //Requires empty public constructor
    }

    public static ListSessionFragment newInstance() {
        return new ListSessionFragment();
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSessionAdapter = new SessionContentAdapter(new ArrayList<SessionDTO>(0), mItemListener, getActivity());
        service = ServiceGenerator.createService(
                SessionService.class,
                getResources().getString(R.string.baseURL));
        actionListener = new ListSessionPresenter(this, service);
    }

    public void onResume() {
        super.onResume();
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);
        actionListener.loadSessions(true, token);
    }


    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_session, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.session_list);
        recyclerView.setAdapter(mSessionAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //SWIPE AND REFRESH
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Restore preferences
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                String token = settings.getString("token", null);
                actionListener.loadSessions(true, token);

            }
        });
        return root;
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
    public void showSessions(List<SessionDTO> sessions) {
        mSessionAdapter.replaceData(sessions);
    }

    @Override
    public void showSessionDetail(Integer sessionId) {
        Intent i = new Intent(getContext(), SessionDetailActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putInt("SESSIONID", sessionId);
        i.putExtras(mBundle);
        startActivity(i);
    }

    @Override
    public void showSessionGame(Integer sessionId) {
        Intent i = new Intent(getContext(), SessionGameActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putInt("SESSIONID", sessionId);
        i.putExtras(mBundle);
        startActivity(i);
    }


    @Override
    public void showErrorMessage(String message) {
        if(getView()!=null){
            Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT);
        }
    }

    SessionItemListener mItemListener = new SessionItemListener() {
        @Override
        public void onSessionClick(SessionDTO clickSession) {
            actionListener.openSessionGame(clickSession);
        }

        @Override
        public void onSessionDetailClick(SessionDTO clickSession) {
            actionListener.openSessionDetail(clickSession);
        }

    };
}
