package com.projects.wens.kandoeteami.organisation;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.projects.wens.kandoeteami.R;
import com.projects.wens.kandoeteami.login.LoginActivity;
import com.projects.wens.kandoeteami.start.SplashActivity;
import com.projects.wens.kandoeteami.themes.ListThemeActivity;
import com.projects.wens.kandoeteami.user.UserActivity;


public class ListOrganisationActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    public static final String PREFS_NAME = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);


        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,  mDrawerLayout, toolbar, 0, 0);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        if (navigationView != null){
            setupDrawerContent(navigationView);
        }

        if (null == savedInstanceState){
            initFragment(ListOrganisationFragment.newInstance());
        }

        //DRAWER
         String username = this.getSharedPreferences(PREFS_NAME, 0).getString("username", null);
         TextView tvUsername = (TextView)findViewById(R.id.nav_username);
         tvUsername.setText(username);
    }



    private void initFragment(Fragment listOrganisationFrag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, listOrganisationFrag);
        transaction.commit();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        //Hier gaan de menu opties uitgewerkt worden
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //TODO: UITWERKING MENU OPTIES
                        Intent i;
                        switch (menuItem.getItemId()){
                            case R.id.nav_ic_organisaitons:
                                i = new Intent(getApplicationContext(), ListOrganisationActivity.class);
                                startActivity(i);
                                break;
                            case R.id.nav_ic_profile:
                                i = new Intent(getApplicationContext(), UserActivity.class);
                                startActivity(i);
                                break;
                            case R.id.nav_ic_logout:
                                //Removing token
                                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("token", "");
                                editor.remove("token");

                                // Commit the edits!
                                editor.commit();

                                i = new Intent(getApplicationContext(), SplashActivity.class);
                                startActivity(i);
                                finish();
                                break;
                            case R.id.nav_ic_kandoes:
                                break;
                            case R.id.nav_ic_newkandoe:
                                break;
                            case R.id.nav_ic_themes:
                                i = new Intent(getApplicationContext(), ListThemeActivity.class);
                                startActivity(i);
                                break;
                            case R.id.nav_ic_help:
                                break;
                            case R.id.nav_ic_about:
                                break;

                        }
                        return false;
                    }
                }
        );
    }
}
