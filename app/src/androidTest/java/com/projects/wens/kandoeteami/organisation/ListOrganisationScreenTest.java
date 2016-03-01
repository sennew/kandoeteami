package com.projects.wens.kandoeteami.organisation;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by senne on 19/02/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ListOrganisationScreenTest {
    //We gaan hier een RULE moeten specifiëren het gaat uw activiteit launchen
    @Rule
    public ActivityTestRule<ListOrganisationActivity> mListOrganisationActivity =
            new ActivityTestRule<ListOrganisationActivity>(ListOrganisationActivity.class);

}
