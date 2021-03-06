package com.projects.wens.kandoeteami.session;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.adapter.ExpandableListViewAdapter;
import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.SessionService;
import com.projects.wens.kandoeteami.session.data.SessionDTO;
import com.projects.wens.kandoeteami.session.data.SessionMode;
import com.projects.wens.kandoeteami.session.data.SessionState;
import com.projects.wens.kandoeteami.themes.data.Card;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nullable;

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
    private TextView tvSessionName;
    private TextView tvSessionDate;
    private TextView tvSessionCurUser;
    private TextView tvSessionTypeAndMode;
    private ImageView imgSession;

    public TextView tvSessionStartTimePrefix;

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
        sessionId = fragment.getArguments().getInt("SESSIONID");

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
        View root = inflater.inflate(R.layout.fragment_session_item, container, false);

        tvSessionCurUser = (TextView) root.findViewById(R.id.txt_session_curuser);
        tvSessionName = (TextView) root.findViewById(R.id.txt_session_name);
        tvSessionDate = (TextView) root.findViewById(R.id.txt_session_date);
        tvSessionTypeAndMode = (TextView) root.findViewById(R.id.txt_session_typeandmode);
        tvSessionStartTimePrefix = (TextView) root.findViewById(R.id.session_start_prefix);
        imgSession = (ImageView) getActivity().findViewById(R.id.header_img);

        collapsing = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        expandUserList = (ExpandableListView) root.findViewById(R.id.session_user_list);

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
    public void showSession(SessionDTO session, List<GroupItem> items, int activeSession, int countSession) {
        tvSessionName.setText(session.getSessionName());
        tvSessionCurUser.setText(session.getUsers().get(0).getUsername());
        if(session.getState() == SessionState.CREATED && session.getMode() == SessionMode.SYNC){
            tvSessionTypeAndMode.setText(String.valueOf(session.getType() + " | " + session.getMode() + " | Waiting for users to start"));
        } else {
            tvSessionTypeAndMode.setText(String.valueOf(session.getType() + " | " + session.getMode() + " | " + session.getState()));
        }

        if(session.getTheme()!=null){
            if (session.getTheme().getIconURL()!=null && session.getTheme().getIconURL().charAt(0) == 'r'){
                Picasso.with(getActivity()).load(PICASSO_BASEURL +  session.getTheme().getIconURL()).into(imgSession);
            } else {
                Picasso.with(this.getContext()).load(session.getTheme().getIconURL()).into(imgSession);
            }
        } else if (session.getSubTheme().getIconURL()!=null){
            if (session.getSubTheme().getIconURL().charAt(0) == 'r'){
                Picasso.with(getActivity()).load(PICASSO_BASEURL +  session.getSubTheme().getIconURL()).into(imgSession);
            } else {
                Picasso.with(this.getContext()).load(session.getSubTheme().getIconURL()).into(imgSession);
            }
        }

        String format = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US); //2016-03-19T07:20:02.295 | yyyy-MM-dd'T'HH:mm:ssZ
        Date start;
        Date now = new Date();

        try {
            start = sdf.parse(session.getStartTime());
            if (session.getState() == SessionState.CREATED) {
                if (start.getTime() > now.getTime()) {
                    //TIME BEFORE START
                    tvSessionStartTimePrefix.setText("Starting in:");
                    String time = getTimeBetweenDateTimes(start.getTime(), now.getTime()); //start - now
                    tvSessionDate.setText(time);
                } else if (now.getTime() > start.getTime()) {
                    tvSessionStartTimePrefix.setText("Session time:");
                    String time = getTimeBetweenDateTimes(now.getTime(), start.getTime()); //now - start
                    tvSessionDate.setText(time);
                }
            } else if (session.getState() == SessionState.IN_PROGRESS) {
                tvSessionStartTimePrefix.setText("Session time:");
                String time = getTimeBetweenDateTimes(now.getTime(), start.getTime()); //now - start
                tvSessionDate.setText(time);
            } if (session.getState() == SessionState.FINISHED) {
                tvSessionStartTimePrefix.setText("Game finished.");
                tvSessionDate.setText("");
            }
        } catch (ParseException e) {
            Log.i("DATE", e.getMessage());
        }
        List<Card> cards = session.getCards();
        for (Card c: cards){
            RelativeLayout basis = new RelativeLayout(getContext());
            basis.setLayoutParams(new RelativeLayout.LayoutParams(450, 350));
            basis.setGravity(Gravity.CENTER);

            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new Gallery.LayoutParams(425, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (c.getImageUrl().charAt(0) == 'r'){
                Picasso.with(this.getContext()).load(PICASSO_BASEURL +  c.getImageUrl()).into(imageView);
            } else {
                Picasso.with(this.getContext()).load(c.getImageUrl()).into(imageView);
            }

            TextView textView = new TextView(getContext());
            TableRow.LayoutParams params = new TableRow.LayoutParams(425,80);

            textView.setLayoutParams(params);
            textView.setBackgroundColor(Color.parseColor("#99000000"));
            textView.setTextColor(Color.WHITE);
            textView.setPadding(10, 0, 5, 0);
            textView.setText(c.getDescription());

            basis.addView(imageView);
            basis.addView(textView);
            horizontalScrollLayout.addView(basis);

        }

        collapsing.setTitle(session.getSessionName());
        adapter.setData(items);
        expandUserList.setAdapter(adapter);
    }

    @Override
    public void showErrorMessage(String message) {
        if(getView()!=null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    public String getTimeBetweenDateTimes(Long time1, Long time2) {
        String timeStringToShow="";
        long different = time1 - time2;
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        long elapsedMinutes = different / minutesInMilli;
        if (elapsedDays == 0) {
            if (elapsedHours == 0) {
                if (elapsedMinutes == 0) {
                    timeStringToShow = String.format("A few seconds");
                } else {
                    timeStringToShow = String.format("%d minutes", elapsedMinutes);
                }
            } else {
                timeStringToShow = String.format("%d hours, %d minutes", elapsedHours, elapsedMinutes);
            }
        } else {
            timeStringToShow = String.format("%d days, %d hours, %d minutes", elapsedDays, elapsedHours, elapsedMinutes);
        }
        return timeStringToShow;
    }
}
