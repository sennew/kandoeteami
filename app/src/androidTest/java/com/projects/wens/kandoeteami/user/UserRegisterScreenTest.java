package com.projects.wens.kandoeteami.user;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.projects.wens.kandoeteami.start.SplashActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by senne on 29/02/2016.
 */
@RunWith(AndroidJUnit4.class)
public class UserRegisterScreenTest {
    //We gaan hier een RULE moeten specifiëren die de activity gaat launchen
    @Rule
    public ActivityTestRule<SplashActivity> mRegisterActivity =
            new ActivityTestRule<SplashActivity>(SplashActivity.class);

    //TEST: ALS JE OP HET MAIN SCREEN KOMT MOET ER OP DE BUTTON
    /*@Test
    public void clickOnRegister_opensViewRegister() throws Exception{
        //Click op de registerView
        onView(withId(R.id.register_button).)
    }*/

}
