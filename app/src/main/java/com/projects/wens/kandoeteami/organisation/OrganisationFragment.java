package com.projects.wens.kandoeteami.organisation;

import android.support.v4.app.Fragment;

/**
 * Created by michaelkees on 29/02/16.
 */
public class OrganisationFragment extends Fragment implements OrganisationContract.View {


    public static Fragment newInstance() {
        return new OrganisationFragment();
    }

    @Override
    public int getOrganisationID() {
        return 0;
    }

    @Override
    public void showOrganisation() {

    }
}
