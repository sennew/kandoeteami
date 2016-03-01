package com.projects.wens.kandoeteami.organisation;

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

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by michaelkees on 29/02/16.
 */
public class OrganisationFragment extends Fragment implements OrganisationContract.View {
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
        Toast.makeText(getContext(), "ORGAID: " + organisationId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String token = settings.getString("token", null);

        //LOAD ORGANISATION METHOD?
        //organisationActionListener.loadOrganisation(token, id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_organisation_item, container, false);
        tvOrganisationTitle = (TextView) root.findViewById(R.id.org_title);
        tvOrganisationDescription = (TextView) root.findViewById(R.id.org_description);
        imgOrganisation = (ImageView) root.findViewById(R.id.org_img);

        tvOrganisationTitle.setText("ORGAID:" + organisationId);
        return root;
    }


    @Override
    public void showSuccesMessage(String message) {

    }

    @Override
    public void showOrganisation(Organisation organisation) {

    }

    @Override
    public String getOrganisationName() {
        return null;
    }

    @Override
    public String getOrganisationDescription() {
        return null;
    }

    @Override
    public List<String> getOrganisationMembers() {
        return null;
    }

    @Override
    public List<String> getOrganisationOrganisers() {
        return null;
    }
}
