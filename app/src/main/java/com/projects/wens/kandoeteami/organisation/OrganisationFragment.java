package com.projects.wens.kandoeteami.organisation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.data.Organisation;
import com.projects.wens.kandoeteami.retrofit.ServiceGenerator;
import com.projects.wens.kandoeteami.retrofit.service.OrganisationService;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrganisationFragment extends Fragment implements OrganisationContract.View {
    private static final String PICASSO_BASEURL = "http://wildfly-teamiip2kdgbe.rhcloud.com/";
    public static final String PREFS_NAME = "MyPrefs";
    private OrganisationService service;
    private OrganisationContract.UserActionListener organisationActionListener;
    private TextView tvOrganisationTitle;
    private TextView tvOrganisationDescription;
    private ImageView imgOrganisation;
    private int organisationId;



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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_organisation_item, container, false);
        tvOrganisationTitle = (TextView) root.findViewById(R.id.org_title);
        tvOrganisationDescription = (TextView) root.findViewById(R.id.org_description);
        imgOrganisation = (ImageView) root.findViewById(R.id.org_img);

        return root;
    }


    @Override
    public void showSuccesMessage(String message) {
        Crouton.makeText(getActivity(), message, Style.CONFIRM).show();
    }

    @Override
    public void showOrganisation(Organisation organisation) {
        tvOrganisationTitle.setText(organisation.getOrganisationName());
        tvOrganisationDescription.setText(organisation.getAddress());
        //Context context = imgOrganisation.getContext();
        //Picasso.with(context).load(PICASSO_BASEURL + organisation.getLogoUrl()).into(imgOrganisation);
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
    public List<String> getOrganisationMembers() {
        //TODO ListView members
        return null;
    }

    @Override
    public List<String> getOrganisationOrganisers() {
        //TODO ListView organisers
        return null;
    }

    @Override
    public void showErrorMessage(String message) {
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
    }
}
