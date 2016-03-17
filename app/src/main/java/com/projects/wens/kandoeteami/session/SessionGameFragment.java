package com.projects.wens.kandoeteami.session;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.session.adapter.SessionCardAdapter;
import com.projects.wens.kandoeteami.session.adapter.SessionCardItemListener;
import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.themes.data.Card;

import java.util.ArrayList;


public class SessionGameFragment extends Fragment implements SessionGameContract.View {
    private SessionService service;
    private SessionGameContract.UserActionListener actionListener;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";

    private int sessionId;

    private TextView tvSessionName;
    private TextView tvSessionDate;
    private TextView tvSessionCurUser;
    private ImageView imgSessionInfo;
    private RecyclerView recyclerView;


    private SessionCardAdapter cardAdapter;

    public static SessionGameFragment fragment;

    public static Fragment newInstance(int sessionid) {
        fragment = new SessionGameFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("SESSIONID", sessionid);
        fragment.setArguments(bundle);
        return fragment;
    }

    SessionCardItemListener itemListener = new SessionCardItemListener() {
        @Override
        public void onCardClick(Card clickCard) {
            //actionListener.openThemeDetail(clickTheme);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(SessionService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        actionListener = new SessionGamePresenter(this, service);
        cardAdapter = new SessionCardAdapter(new ArrayList<Card>(0),itemListener, getActivity());
        sessionId = fragment.getArguments().getInt("SESSIONID");
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);
        actionListener.loadSession(token, sessionId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_session_game_item, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.list_session_cards);
        recyclerView.setAdapter(cardAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        tvSessionCurUser = (TextView) root.findViewById(R.id.txt_session_curuser);
        tvSessionName = (TextView) root.findViewById(R.id.txt_session_name);
        tvSessionDate = (TextView) root.findViewById(R.id.txt_session_date);
        imgSessionInfo = (ImageView) root.findViewById(R.id.img_session_info);

        return root;
    }


    @Override
    public void showSession(SessionDTO session) {
        tvSessionName.setText(session.getSessionName());
        tvSessionCurUser.setText(session.getUsers().get(0).getUsername());
        tvSessionDate.setText(session.getStartTime());

        Toast.makeText(getActivity(), "SIZE CARDS: " + session.getCards().size(), Toast.LENGTH_SHORT).show();
        cardAdapter.replaceData(session.getCards());
    }

    @Override
    public void showErrorMessage(String s) {

    }
}
