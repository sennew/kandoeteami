package com.projects.wens.kandoeteami.organisation;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.adapter.ExpandableListViewAdapter;
import com.projects.wens.kandoeteami.organisation.data.GroupItem;
import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class OrganisationFragment extends Fragment implements OrganisationContract.View {
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    public static final String PREFS_NAME = "MyPrefs";
    private OrganisationService service;
    private OrganisationContract.UserActionListener organisationActionListener;
    private TextView tvOrganisationTitle;
    private TextView tvOrganisationDescription;
    private ImageView imgOrganisation;
    private CollapsingToolbarLayout collapsing;
    private Dialog allert;
    private TextView dialogDescription;
    private Button dialogButton;
    private int organisationId;
    private int descriptionlength;
    private ExpandableListViewAdapter adapter;
    private ExpandableListView listview;

    public static Fragment newInstance() {
        return new OrganisationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(OrganisationService.class, getResources().getString(R.string.baseURL));
        organisationActionListener = new OrganisationPresenter(this, service);
        adapter = new ExpandableListViewAdapter(getContext());

        //TODO: GET ID from BUNDLE
        organisationId = (int) getActivity().getIntent().getExtras().get("ORGAID");
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);

        //LOAD ORGANISATION METHOD?
        organisationActionListener.loadOrganisation(token, organisationId);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_organisation_item, container, false);
        tvOrganisationDescription = (TextView) root.findViewById(R.id.org_description);
        imgOrganisation = (ImageView) getActivity().findViewById(R.id.header_img);
        collapsing = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        listview = (ExpandableListView) root.findViewById(R.id.users_list);
        listview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (listview.isGroupExpanded(groupPosition)) {
                    listview.collapseGroup(groupPosition);
                } else {
                    listview.expandGroup(groupPosition);
                }
                return true;
            }
        });



        allert = new Dialog(getContext());
        allert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        allert.setContentView(inflater.inflate(R.layout.dialog, null));
        dialogDescription = (TextView) allert.findViewById(R.id.dialog_description);
        dialogButton = (Button) allert.findViewById(R.id.dialog_button);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allert.cancel();
            }
        });
        return root;
    }


    @Override
    public void showSuccesMessage(String message) {
        Crouton.makeText(getActivity(), message, Style.CONFIRM).show();
    }

    @Override
    public void showOrganisation(Organisation organisation, GroupItem item) {
        Log.e("SIZE", "SIZE: " + item.getChildren().size());
        descriptionlength = organisation.getAddress().length();
        if (descriptionlength > 33){
            tvOrganisationDescription.setText(organisation.getAddress().substring(0, 33) + ".....");
            dialogDescription.setText(organisation.getAddress());
            tvOrganisationDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allert.show();
                }
            });
        } else {
            tvOrganisationDescription.setText(organisation.getAddress());
        }

        if (organisation.getLogoURL().charAt(0) == 'r') {
            Picasso.with(this.getContext()).load(PICASSO_BASEURL + organisation.getLogoURL()).into(imgOrganisation);
        } else {
            Picasso.with(this.getContext()).load(organisation.getLogoURL()).into(imgOrganisation);
        }
        collapsing.setTitle(organisation.getOrganisationName());

        List<GroupItem> groupItems = new ArrayList<GroupItem>();
        groupItems.add(item);
        adapter.setData(groupItems);
        listview.setAdapter(adapter);
    }

    @Override
    public String getOrganisationName() {
        return tvOrganisationTitle.getText().toString();
    }

    @Override
    public String getOrganisationDescription() {
        return tvOrganisationDescription.getText().toString();
    }

    @Override
    public void showErrorMessage(String message) {
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
    }

}
