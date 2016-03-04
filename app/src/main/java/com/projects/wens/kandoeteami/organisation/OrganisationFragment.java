package com.projects.wens.kandoeteami.organisation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.adapter.OrganisationMemberAdapter;
import com.projects.wens.kandoeteami.organisation.adapter.OrganisationOrganiserAdapter;
import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.projects.wens.kandoeteami.user.data.User;
import com.squareup.picasso.Picasso;

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
    private int organisationId;
    private ListView lstMembers;
    private ListView lstOrganisers;


    public static Fragment newInstance() {
        return new OrganisationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ServiceGenerator.createService(OrganisationService.class, "http://wildfly-teamiip2kdgbe.rhcloud.com/api");
        organisationActionListener = new OrganisationPresenter(this, service);


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
        organisationActionListener.loadMembers(token, organisationId);
        organisationActionListener.loadOrganisers(token, organisationId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_organisation_item, container, false);
        tvOrganisationDescription = (TextView) root.findViewById(R.id.org_description);

        imgOrganisation = (ImageView) getActivity().findViewById(R.id.header_img);
        collapsing = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);

        return root;
    }


    @Override
    public void showSuccesMessage(String message) {
        Crouton.makeText(getActivity(), message, Style.CONFIRM).show();
    }

    @Override
    public void showOrganisation(Organisation organisation) {

        tvOrganisationDescription.setText(organisation.getAddress());
        if (organisation.getLogoURL().charAt(0) == 'r') {
            Picasso.with(this.getContext()).load(PICASSO_BASEURL + organisation.getLogoURL()).into(imgOrganisation);
        } else {
            Picasso.with(this.getContext()).load(organisation.getLogoURL()).into(imgOrganisation);
        }
        collapsing.setTitle(organisation.getOrganisationName());
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

    @Override
    public void showMembers(List<User> users) {
        if (users != null && !users.isEmpty()) {
            OrganisationMemberAdapter adapter = new OrganisationMemberAdapter(getActivity(), users);
            if(lstMembers!=null) {
                lstMembers.setAdapter(adapter);
            }
        }
    }

    @Override
    public void showOrganisers(List<User> users) {
        if (users != null && !users.isEmpty()) {
            OrganisationOrganiserAdapter adapter = new OrganisationOrganiserAdapter(getActivity(), users);
            if(lstOrganisers!=null) {
                lstOrganisers.setAdapter(adapter);
            }
        }

    }
}
