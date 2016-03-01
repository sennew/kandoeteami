package com.projects.wens.kandoeteami.organisation;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.projects.wens.kandoeteami.organisation.data.Organisation;

import org.junit.Rule;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class OrganisationScreenTest {
    @Rule
    public ActivityTestRule<OrganisationActivity> organisationActivityActivityTestRule =
            new ActivityTestRule<OrganisationActivity>(OrganisationActivity.class);



}
