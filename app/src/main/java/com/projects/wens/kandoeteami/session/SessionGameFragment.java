package com.projects.wens.kandoeteami.session;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.projects.wens.kandoeteami.websockets.ListenerSubscription;
import com.projects.wens.kandoeteami.websockets.ListenerWSNetwork;
import com.projects.wens.kandoeteami.websockets.Stomp;
import com.projects.wens.kandoeteami.websockets.Subscription;


import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


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
        service = ServiceGenerator.createService(SessionService.class, getResources().getString(R.string.baseURL));
        actionListener = new SessionGamePresenter(this, service);
        cardAdapter = new SessionCardAdapter(new ArrayList<Card>(0),itemListener, getActivity());
        sessionId = fragment.getArguments().getInt("SESSIONID");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




        connectWebSocket();
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

    private Stomp stomp;
    Thread thread = new Thread(new Runnable(){
        @Override
        public void run() {

        }
    });

    private void connectWebSocket() {
        new WebTask().execute("");
    }

    public void sendMessage(View view) {
        // EditText editText = (EditText)getActivity().findViewById(R.id.message);

        //editText.setText("");
    }

    @Override
    public void showErrorMessage(String s) {

    }

    private void connection() {

    }

    private class WebTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Map<String,String> headersSetup = new HashMap<String,String>();
            stomp = new Stomp("ws://wildfly-teamiip2kdgbe.rhcloud.com/circleSession", headersSetup, new ListenerWSNetwork() {
                @Override
                public void onState(int state) {
                    Log.i("WEBSOCKET", String.valueOf(state));
                }
            });
            stomp.connect();
            stomp.subscribe(new Subscription("/topic/chat", new ListenerSubscription() {
                @Override
                public void onMessage(Map<String, String> headers, String body) {
                    Log.i("WEBSOCKET", "OnMessage: " + body);
                }
            }));
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            //TextView txt = (TextView) findViewById(R.id.output);
            //txt.setText("Executed"); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you

            stomp.disconnect();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
