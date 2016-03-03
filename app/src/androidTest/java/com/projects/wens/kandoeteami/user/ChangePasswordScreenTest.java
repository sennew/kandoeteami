package com.projects.wens.kandoeteami.user;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by michaelkees on 03/03/16.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ChangePasswordScreenTest {

    @Rule
    public ActivityTestRule<ChangeUserPasswordActivity> testRule =
            new ActivityTestRule<ChangeUserPasswordActivity>(ChangeUserPasswordActivity.class);

    @Test
    public void checkUpdatePassword() throws Exception{

    }

}
