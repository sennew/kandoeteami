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

    //TEST: Als je op een kaartje drukt ga je naar een overzicht van de themas
    /*@Test
    public void clickOnOrganisation_opensViewThemas() throws Exception{
        //Click op een kaartje
        onView(withId(R.id.cardView)).perform(click());
        //Kijk of er naar de detailView gegaan wordt
        onView(withId(R.id.listThemes)).check(matches(isDisplayed()));
    }

    //TEST: Als je op de detail button klikt moet je naar het detail scherm gaan
    @Test
    public void clickDetailButton_openDetailOrganisation() throws Exception{
        //Click detail Button
        onView(withId(R.id.detail_button)).perform(click());
        //Kijk of nu de detailView getoond wordt
        onView(withId(R.id.detail_view)).check(matches(isDisplayed()));
    }*/
}
