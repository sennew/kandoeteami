package com.projects.wens.kandoeteami.session;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.adapter.ExpandableListViewAdapter;
import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.session.data.SessionDTO;

import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by michaelkees on 18/03/16.
 */
public class SessionDetailFragment extends Fragment implements SessionDetailContract.View {

    private SessionService service;
    private SessionDetailContract.UserActionListener themeActionListener;

    private int sessionId;

    private LinearLayout horizontalScrollLayout;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";

    public static SessionDetailFragment fragment;

    private ExpandableListView expandUserList;
    private ExpandableListViewAdapter adapter;

    private CollapsingToolbarLayout collapsing;


    public static SessionDetailFragment newInstance(int sessionId) {
        fragment = new SessionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("SESSIONID", sessionId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(SessionService.class, getResources().getString(R.string.baseURL));
        themeActionListener = new SessionDetailPresenter(this, service);
        adapter = new ExpandableListViewAdapter(getContext());

        //TODO: REQUEST FROM BUNDLE TO GET THEMEID FROM ACTIVITY
        sessionId = fragment.getArguments().getInt("ThemeId");

    }

    @Override
    public void onResume() {
        super.onResume();
        horizontalScrollLayout.removeAllViews();
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);

        //LOAD THEME
        themeActionListener.loadSession(token, sessionId);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_theme_item, container, false);


        collapsing = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        expandUserList = (ExpandableListView) root.findViewById(R.id.users_list);

        expandUserList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandUserList.isGroupExpanded(groupPosition)){
                    expandUserList.collapseGroup(groupPosition);
                } else {
                    expandUserList.expandGroup(groupPosition);
                }
                return true;
            }
        });
        expandUserList.setFocusable(false);
        horizontalScrollLayout = (LinearLayout) root.findViewById(R.id.session_cards_gallery);


        return root;
    }

    @Override
    public void showSuccesMessage(String message) {

    }

    @Override
    public void showSession(SessionDTO theme, List<GroupItem> items, int activeSession, int countSession) {

    }

    @Override
    public void showErrorMessage(String message) {

    }
}
