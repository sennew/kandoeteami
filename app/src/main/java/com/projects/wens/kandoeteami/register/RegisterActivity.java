package com.projects.wens.kandoeteami.register;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.login.LoginFragment;

public class RegisterActivity extends AppCompatActivity {

    private NavigationView upDrawerContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        if (navigationView != null){
            setupDrawerContent(navigationView);
        }

        if (null == savedInstanceState){
            initFragment(RegisterFragment.newInstance());
        }
    }



    private void initFragment(Fragment registerFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, registerFragment);
        transaction.commit();
    }


    private void setupDrawerContent(NavigationView navigationView) {
        //Hier gaan de menu opties uitgewerkt worden
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //TODO: UITWERKING MENU OPTIES
                        return false;
                    }
                }
        );
    }
}
