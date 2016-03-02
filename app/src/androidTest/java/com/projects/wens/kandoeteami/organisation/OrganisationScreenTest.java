package com.projects.wens.kandoeteami.organisation;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.organisation.data.Organisation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class OrganisationScreenTest {

    @Rule
    public ActivityTestRule<OrganisationActivity> organisationActivityActivityTestRule =
            new ActivityTestRule<OrganisationActivity>(OrganisationActivity.class);

    @Test
    public void testShouldShowOrganisationName() throws Exception {
        //organisation name/title moet getoond worden
        onView(withId(R.id.org_title)).check(matches(isDisplayed()));
    }
}
