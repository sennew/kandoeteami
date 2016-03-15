package com.projects.wens.kandoeteami.themes;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
import com.projects.wens.kandoeteami.retrofit.service.ThemeService;
import com.projects.wens.kandoeteami.themes.data.Card;
import com.projects.wens.kandoeteami.themes.data.Theme;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by senne on 10/03/2016.
 */
public class ThemeDetailFragment extends Fragment implements ThemeDetailContract.View {
    private ThemeService service;
    private ThemeDetailContract.UserActionListener themeActionListener;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";

    private int themeId;

    private TextView themeDescription;
    private ImageView imgTheme;
    private CollapsingToolbarLayout collapsing;

    private ExpandableListView expandListThemeUsers;
    private ExpandableListViewAdapter adapter;

    private Dialog allert;
    private TextView dialogDescription;
    private Button dialogButtonOk;

    private LinearLayout horizontalScrollLayout;

    private TextView activeSessions;
    private TextView countSessions;
    private TextView subThemas;

    public static ThemeDetailFragment fragment;

    public static Fragment newInstance(int themeId) {
        fragment = new ThemeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ThemeId", themeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(ThemeService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        themeActionListener = new ThemeDetailPresenter(this, service);
        adapter = new ExpandableListViewAdapter(getContext());

        //TODO: REQUEST FROM BUNDLE TO GET THEMEID FROM ACTIVITY
        themeId = fragment.getArguments().getInt("ThemeId");

    }

    @Override
    public void onResume() {
        super.onResume();
        horizontalScrollLayout.removeAllViews();
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);

        //LOAD THEME
        themeActionListener.loadTheme(token, themeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_theme_item, container, false);

        themeDescription = (TextView) root.findViewById(R.id.theme_description);
        themeDescription.requestFocus();
        imgTheme = (ImageView) getActivity().findViewById(R.id.header_img);
        collapsing = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        expandListThemeUsers = (ExpandableListView) root.findViewById(R.id.users_theme_list);

        expandListThemeUsers.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandListThemeUsers.isGroupExpanded(groupPosition)){
                    expandListThemeUsers.collapseGroup(groupPosition);
                } else {
                    expandListThemeUsers.expandGroup(groupPosition);
                }
                return true;
            }
        });
        expandListThemeUsers.setFocusable(false);

        allert = new Dialog(getContext());
        allert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        allert.setContentView(inflater.inflate(R.layout.dialog, null));
        dialogDescription = (TextView) allert.findViewById(R.id.dialog_description);
        dialogButtonOk = (Button) allert.findViewById(R.id.dialog_button);
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allert.cancel();
            }
        });

        horizontalScrollLayout = (LinearLayout) root.findViewById(R.id.theme_cards_gallery);

        activeSessions = (TextView) root.findViewById(R.id.theme_active_session);
        countSessions = (TextView) root.findViewById(R.id.theme_sessions);
        subThemas = (TextView) root.findViewById(R.id.theme_subthemes);


        return root;
    }

    @Override
    public void showSuccesMessage(String message) {

    }

    @Override
    public void showTheme(Theme theme, List<GroupItem> item, int activeSession, int countSession) {
        int descriptionLength = theme.getDescription().length();
        if (descriptionLength > 33){
            themeDescription.setText(theme.getDescription().substring(0,33) + "....");
            //TODO: HIER MOET DIALOG AANGEMAAKT WORDEN
            dialogDescription.setText(theme.getDescription());
            themeDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allert.show();
                }
            });
        } else {
            themeDescription.setText(theme.getDescription());
        }

        if (theme.getIconURL().charAt(0) == 'r'){
            Picasso.with(this.getContext()).load(PICASSO_BASEURL +  theme.getIconURL()).into(imgTheme);
        } else {
            Picasso.with(this.getContext()).load(theme.getIconURL()).into(imgTheme);
        }

        countSessions.setText(""+countSession);
        activeSessions.setText(""+activeSession);
        if (theme.getCountSubthemes() == 0){
            subThemas.setText("none");
        }else {
            subThemas.setText(theme.getCountSubthemes());
        }


        //Opvragen cards van een thema
        List<Card> cards = theme.getCards();

        for (Card c: cards){
            RelativeLayout basis = new RelativeLayout(getContext());
            basis.setLayoutParams(new RelativeLayout.LayoutParams(450, 350));
            basis.setGravity(Gravity.CENTER);

            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new Gallery.LayoutParams(425, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (c.getImageUrl().charAt(0) == 'r'){
                Picasso.with(this.getContext()).load(PICASSO_BASEURL +  theme.getIconURL()).into(imageView);
            } else {
                Picasso.with(this.getContext()).load(theme.getIconURL()).into(imageView);
            }

            TextView textView = new TextView(getContext());
            TableRow.LayoutParams params = new TableRow.LayoutParams(425,80);

            textView.setLayoutParams(params);
            textView.setBackgroundColor(Color.parseColor("#99000000"));
            textView.setTextColor(Color.WHITE);
            textView.setPadding(10,0,5,0);
            textView.setText(c.getDescription());

            basis.addView(imageView);
            basis.addView(textView);
            horizontalScrollLayout.addView(basis);
        }


        collapsing.setTitle(theme.getThemeName());
        adapter.setData(item);
        expandListThemeUsers.setAdapter(adapter);
    }

    @Override
    public void showErrorMessage(String message) {

    }
}
