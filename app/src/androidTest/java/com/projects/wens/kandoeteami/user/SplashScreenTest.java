package com.projects.wens.kandoeteami.user;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.start.SplashActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by senne on 29/02/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class SplashScreenTest {
    //We gaan hier een Rule moeten specifiëren die de activity gaat launchen
    @Rule
    public ActivityTestRule<SplashActivity> mSplashActivity =
            new ActivityTestRule<SplashActivity>(SplashActivity.class);

    @Before
    public void intentSplashScreen(){
        Intent startIntent = new Intent();
        mSplashActivity.launchActivity(startIntent);
    }
    //TEST: Als je op het main screen komt moet er op het registerButton klikken en Register tonen
    @Test
    public void TestClickOnRegister_opensViewRegister() throws Exception {
        //Click op de register_button
        onView(withId(R.id.register_button)).perform(click());
        //Kijk of er naar de registerPage wordt gegaan
        onView(withId(R.id.register_linear)).check(matches(isDisplayed()));
    }

    @Test
    public void TestClickOnLogin_opensViewLogin() throws Exception{
        //Click op login_button
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.login_linear)).check(matches(isDisplayed()));
    }
}
