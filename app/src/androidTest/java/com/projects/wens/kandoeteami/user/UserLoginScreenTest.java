package com.projects.wens.kandoeteami.user;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by senne on 29/02/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class UserLoginScreenTest {
    //We gaan hier een RULE moeten specifiëren die de activity gaat launchen
    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityTestRule =
            new ActivityTestRule<LoginActivity>(LoginActivity.class);


    //INLOGGEN EN NAAR ORGANIASTIONOVERVIEW GAAN
    @Test
    public void checkUserLogin() throws Exception{
        String userName = "ArneLauryssens";
        String password = "test123";

        //VELDEN: GEBRUIKERSNAAM -- PASWOORD
        onView(withId(R.id.login_username)).perform(typeText(userName));
        onView(withId(R.id.login_password)).perform(typeText(password),closeSoftKeyboard());

        //LOGIN BUTTON
        onView(withId(R.id.btnLogin)).perform(click());
    }
}
