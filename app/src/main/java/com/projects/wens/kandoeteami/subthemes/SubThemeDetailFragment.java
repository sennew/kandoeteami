package com.projects.wens.kandoeteami.subthemes;

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
import com.projects.wens.kandoeteami.retrofit.service.SubThemaService;
import com.projects.wens.kandoeteami.subthemes.data.SubTheme;
import com.projects.wens.kandoeteami.themes.data.Card;
import com.squareup.picasso.Picasso;

import java.util.List;


public class SubThemeDetailFragment extends Fragment implements SubThemeDetailContract.View {
    private SubThemaService service;
    private SubThemeDetailContract.UserActionListener subThemeActionListener;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    private int subThemeId;
    private TextView subthemeDescription;
    private ImageView imgSubtheme;
    private CollapsingToolbarLayout collapsing;
    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter adapter;
    private LinearLayout horizontalScrollLayout;
    private TextView activeSessions;
    private TextView countSessions;
    private Dialog allert;
    private TextView dialogDescription;
    private Button dialogButton;

    public static SubThemeDetailFragment fragment;

    public static Fragment newInstance(int subThemeId){
        fragment = new SubThemeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("subThemeId", subThemeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(SubThemaService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api" );
        subThemeActionListener = new SubThemeDetailPresenter(this,service);
        adapter = new ExpandableListViewAdapter(getContext());
        subThemeId = fragment.getArguments().getInt("subThemeId");
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME,0);
        String token = settings.getString("token", null);
        subThemeActionListener.loadSubTheme(token, subThemeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_subtheme_item,container, false);

        subthemeDescription = (TextView) root.findViewById(R.id.subtheme_description);
        subthemeDescription.requestFocus();

        imgSubtheme = (ImageView) getActivity().findViewById(R.id.header_img);

        collapsing = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);

        expandableListView = (ExpandableListView) root.findViewById(R.id.users_subtheme_list);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandableListView.isGroupExpanded(groupPosition)) {
                    expandableListView.collapseGroup(groupPosition);
                } else {
                    expandableListView.expandGroup(groupPosition);
                }
                return true;
            }
        });
        expandableListView.setFocusable(false);

        allert = new Dialog(getContext());
        allert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        allert.setContentView(inflater.inflate(R.layout.dialog, null));
        dialogDescription = (TextView) allert.findViewById(R.id.dialog_description);
        dialogButton = (Button) allert.findViewById(R.id.dialog_button);
        dialogButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                allert.cancel();
            }
        });


        horizontalScrollLayout = (LinearLayout) root.findViewById(R.id.subtheme_cards_gallery);

        activeSessions = (TextView) root.findViewById(R.id.subtheme_active_session);
        countSessions = (TextView) root.findViewById(R.id.subtheme_sessions);

        return root;
    }

    @Override
    public void showSuccesMessage(String message) {

    }

    @Override
    public void showSubTheme(SubTheme subTheme, List<GroupItem> items, int activeSession, int coutSession) {
        int descriptionLength = subTheme.getDescription().length();
        if (descriptionLength > 33){
            subthemeDescription.setText(subTheme.getDescription().substring(0,33) + "....");
            dialogDescription.setText(subTheme.getDescription());

            subthemeDescription.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    allert.show();
                }
            });
        } else {
            subthemeDescription.setText(subTheme.getDescription());
        }

        if (subTheme.getIconURL().charAt(0) == 'r'){
            Picasso.with(this.getContext()).load(PICASSO_BASEURL + subTheme.getIconURL()).into(imgSubtheme);
        } else {
            Picasso.with(this.getContext()).load(subTheme.getIconURL()).into(imgSubtheme);
        }

        countSessions.setText(""+coutSession);
        activeSessions.setText("" + activeSession);

        List<Card> cards = subTheme.getCards();

        for (Card c: cards){
            RelativeLayout basis = new RelativeLayout(getContext());
            basis.setLayoutParams(new RelativeLayout.LayoutParams(450, 350));
            basis.setGravity(Gravity.CENTER);

            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new Gallery.LayoutParams(425, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (c.getImageUrl().charAt(0) == 'r'){
                Picasso.with(this.getContext()).load(PICASSO_BASEURL +  subTheme.getIconURL()).into(imageView);
            } else {
                Picasso.with(this.getContext()).load(subTheme.getIconURL()).into(imageView);
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

        collapsing.setTitle(subTheme.getThemeName());
        adapter.setData(items);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void showErrorMessage(String message) {

    }
}
