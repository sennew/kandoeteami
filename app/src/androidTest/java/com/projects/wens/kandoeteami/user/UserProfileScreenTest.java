package com.projects.wens.kandoeteami.user;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.projects.wens.kandoeteami.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by michaelkees on 03/03/16.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class UserProfileScreenTest {

    //We gaan hier een RULE moeten specifiëren die de activity gaat launchen
    @Rule
    public ActivityTestRule<UserActivity> mUserActivityRule =
            new ActivityTestRule<UserActivity>(UserActivity.class);

    @Test
    public void checkSaveUser() throws Exception{

    }

    @Test
    public void testShouldChangePasswordActivity() throws Exception {

    }
}
