package com.projects.wens.kandoeteami.user;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.register.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by senne on 29/02/2016.
 * RUNWITH GENYMOTION
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserRegisterScreenTest {
    //We gaan hier een RULE moeten specifiëren die de activity gaat launchen
    @Rule
    public ActivityTestRule<RegisterActivity> mRegisterActivity =
            new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

    //TEST: OF DE NIEUWE GEBRUIKER AANGEMAAKT WORDT EN NAAR HET LOGIN SCHERM GEGAAN WORDT
    @Test
    public void registerNewUserTest()throws Exception{
        //VALUES VOOR NIEUWE GEBRUIKER
        String userName = "Espresso4";
        String email = "espresso4@hotmail.com";
        String password = "espresso";

        //Deze velden moeten kunnen opgevuld worden in de view
        onView(withId(R.id.reg_username)).perform(typeText(userName));
        onView(withId(R.id.reg_email)).perform(typeText(email));
        onView(withId(R.id.reg_password)).perform(typeText(password));
        onView(withId(R.id.reg_retypepassword)).perform(typeText(password));

        //Mogenlijkheid tot op registerButton te klikken
        onView(withId(R.id.register_button)).perform(click());

        //Nu moet de loginView getoond worden
        onView(withId(R.id.login_linear)).check(matches(isDisplayed()));
    }


}
