package com.projects.wens.kandoeteami.login;

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
import com.projects.wens.kandoeteami.organisation.ListOrganisationFragment;

/**
 * Created by senne on 23/02/2016.
 */
public class LoginActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        if (navigationView != null){
            setupDrawerContent(navigationView);
        }

        if (null == savedInstanceState){
            initFragment(LoginFragment.newInstance());
        }
    }

    private void initFragment(Fragment loginFrag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, loginFrag);
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
