package com.projects.wens.kandoeteami.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.projects.wens.kandoeteami.R;

/**
 * Created by senne on 23/02/2016.
 */
public class LoginActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private static final CallbackManager CALLBACK_MANAGER = CallbackManager.Factory.create();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noside);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (null == savedInstanceState){
            initFragment(LoginFragment.newInstance());
        }

        FacebookSdk.sdkInitialize(getApplicationContext());


    }

    private void initFragment(Fragment loginFrag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, loginFrag);
        transaction.commit();
    }


}
