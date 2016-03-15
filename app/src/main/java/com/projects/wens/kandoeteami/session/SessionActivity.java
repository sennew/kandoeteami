package com.projects.wens.kandoeteami.session;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.projects.wens.kandoeteami.R;

/**
 * Created by senne on 15/03/2016.
 */
public class SessionActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_session);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Viewpager voor de tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.tabs);


    }
}
